package com.ejie.zz99.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.TableManager;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository()
@Transactional()
public abstract class GenericoDaoImpl<T> extends BaseDaoImpl implements GenericoDao<T> {

	private final Class<T> type;

	protected static final String DEFAULT_WHERE = " WHERE 1=1 ";

	private RowMapper<T> rwMap;
	private RowMapper<T> rwMapPk;
	private String[] whiteList;

	/**
	 * @param type Class<T>
	 */
	protected GenericoDaoImpl(Class<T> type, RowMapper<T> rwMap, RowMapper<T> rwMapPk, String[] whiteList) {
		this.type = type;
		this.rwMap = rwMap;
		this.rwMapPk = rwMapPk;
		this.whiteList = whiteList;
	}

	/*
	 * OPERACIONES CRUD
	 */

	/**
	 * Inserts a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	public T add(T bean) {
		throw new NotImplementedException();
	}

	/**
	 * Updates a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	public T update(T bean) {
		throw new NotImplementedException();
	}

	/**
	 * Removes a single row in the T table.
	 *
	 * @param bean T
	 * @return
	 */
	@Override
	public void remove(T bean) {
		throw new NotImplementedException();
	}

	/**
	 * Finds a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	@Transactional(readOnly = true)
	public T find(T bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		this.getSelect(query);
		this.getFrom(query);
		this.getWherePK(query, bean, params);

		List<T> beanList = this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());

		return DataAccessUtils.uniqueResult(beanList);
	}

	/**
	 * Finds a list of rows in the T table.
	 * 
	 * @param bean            T
	 * @param TableRequestDto tableRequestDto
	 * @return List<T>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(T bean, TableRequestDto tableRequestDto) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		this.getSelect(query);
		this.getFrom(query);

		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		this.getWhere(query, bean, params, false);

		this.getGroupBy(query);

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, this.whiteList);
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Counts rows in the T table.
	 * 
	 * @param bean T
	 * @return Long
	 */
	@Override
	@Transactional(readOnly = true)
	public Long findAllCount(T bean) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT COUNT(1)");
		this.getFrom(query);

		// Where clause & Params
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		this.getWhere(query, bean, params, false);

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	/**
	 * Reorder the data list of T selected for rup_table
	 * 
	 * @param bean            T
	 * @param TableRequestDto tableRequestDto
	 * @return List<TableRowDto<T>>
	 */
	@Override()
	public List<TableRowDto<T>> reorderSelection(T bean, TableRequestDto tableRequestDto) {
		// SELECT
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		this.getSelect(query);
		// FROM
		this.getFrom(query);
		// FILTRADO
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		this.getWhere(query, bean, params, false);

		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = TableManager.getReorderQuery(query, tableRequestDto, this.type, params,
				this.getPK());

		return this.getJdbcTemplate().query(sbReorderSelectionSQL.toString(),
				new RowNumResultSetExtractor<T>(this.rwMapPk, tableRequestDto), params.toArray());
	}

	/**
	 * Search method for rup_table
	 * 
	 * @param filterParams    T
	 * @param searchParams    T
	 * @param TableRequestDto tableRequestDto
	 * @return List<TableRowDto<T>>
	 */
	@Override()
	public List<TableRowDto<T>> search(T filterParams, T searchParams, TableRequestDto tableRequestDto) {
		// SELECT
		List<Object> filterParamList = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		this.getSelect(query);
		// FROM
		this.getFrom(query);
		// TABLAS_ALIAS
		List<String> fromAlias = new ArrayList<String>();
		fromAlias.add(" t1 ");

		// FILTRADO
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		this.getWhere(query, filterParams, filterParamList, false);

		// BUSQUEDA
		List<Object> searchParamList = new ArrayList<Object>();

		StringBuilder searchSQL = new StringBuilder();
		this.getWhere(searchSQL, searchParams, searchParamList, true);
		// SQL
		StringBuilder sbReorderSelectionSQL = TableManager.getSearchQuery(query, tableRequestDto, this.type,
				filterParamList, searchSQL.toString(), searchParamList, fromAlias, this.getPK());

		return this.getJdbcTemplate().query(sbReorderSelectionSQL.toString(),
				new RowNumResultSetExtractor<T>(this.rwMapPk, tableRequestDto), filterParamList.toArray());
	}

	@Override()
	public List<T> getSelected(T bean, TableRequestDto tableRequestDto) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder();
		this.getSelect(query);
		this.getFrom(query);
		query.append(GenericoDaoImpl.DEFAULT_WHERE);
		this.getWhere(query, bean, params, false);

		if (tableRequestDto != null) {
			this.generarWhereMultiSelect(query, this.getPK(), tableRequestDto, params);
		}

		return this.getJdbcTemplate().query(query.toString(), this.rwMap, params.toArray());
	}

	@Override
	protected Integer getNextVal(String secuencia) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append(secuencia).append(".NEXTVAL FROM DUAL");
		return this.getJdbcTemplate().queryForObject(query.toString(), Integer.class);
	}

	/*
	 * MÉTODOS PRIVADOS
	 */

	/**
	 * @return String
	 */
	protected abstract void getSelect(StringBuilder select);

	/**
	 * @return String
	 */
	protected abstract void getFrom(StringBuilder from);

	/**
	 * @return String
	 */
	protected abstract String getPK();

	/**
	 * @param bean   T
	 * @param params List<Object>
	 * @return String
	 */
	protected abstract void getWherePK(StringBuilder wherePk, T bean, List<Object> params);

	/**
	 * @param bean   T
	 * @param params List<Object>
	 * @return String
	 */
	protected abstract void getWhere(StringBuilder where, T bean, List<Object> params, Boolean search);

	protected void getGroupBy(StringBuilder query) {

	}

	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4096;
}
