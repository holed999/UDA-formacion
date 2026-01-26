package com.ejie.zz99.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.zz99.dao.GenericoDao;

/**
 * @author vdorantes
 *
 * @param <T> Tipo
 */
public abstract class GenericoServiceImpl<T> implements GenericoService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericoServiceImpl.class);

	private final Class<T> type;

	/**
	 * @return GenericoDao<T>
	 */
	protected abstract GenericoDao<T> getDao();

	protected GenericoServiceImpl(Class<T> type) {
		this.type = type;
	}

	/**
	 * Inserts a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public T add(T bean) {
		return this.getDao().add(bean);
	}

	/**
	 * Updates a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public T update(T bean) {
		return this.getDao().update(bean);
	}

	/**
	 * Finds a single row in the T table.
	 *
	 * @param bean T
	 * @return T
	 */
	@Override
	public T find(T bean) {
		return this.getDao().find(bean);
	}

	/**
	 * Deletes a single row in the T table.
	 *
	 * @param bean T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void remove(T bean) {
		this.getDao().remove(bean);
	}

	/**
	 * Finds a list of rows in the T table.
	 *
	 * @param bean            T
	 * @param TableRequestDto tableRequestDto
	 * @return List<T>
	 */
	@Override
	public List<T> findAll(T bean, TableRequestDto tableRequestDto) {
		return this.getDao().findAll(bean, tableRequestDto);
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Filter method in the T table.
	 *
	 * @param filter          T
	 * @param TableRequestDto tableRequestDto
	 * @param startsWith      Boolean
	 * @return JQGridResponseDto<T>
	 */
	@Override
	public TableResponseDto<T> filter(T filter, TableRequestDto tableRequestDto) {
		List<T> listaT = this.getDao().findAll(filter, tableRequestDto);
		Long recordNum = this.getDao().findAllCount(filter);
		return new TableResponseDto<T>(tableRequestDto, recordNum, listaT);
	}

	/**
	 * Searches rows in the T table.
	 *
	 * @param filterT         T
	 * @param searchT         T
	 * @param TableRequestDto tableRequestDto
	 * @param startsWith      Boolean
	 * @return List<TableRowDto<T>>
	 */
	@Override
	public List<TableRowDto<T>> search(T filterT, T searchT, TableRequestDto tableRequestDto) {
		return this.getDao().search(filterT, searchT, tableRequestDto);
	}

	/**
	 * Reorder the selection made in T table.
	 *
	 * @param filterT         T
	 * @param TableRequestDto tableRequestDto
	 * @param startsWith      Boolean
	 * @return Object
	 */
	@Override
	public Object reorderSelection(T filterT, TableRequestDto tableRequestDto) {
		return this.getDao().reorderSelection(filterT, tableRequestDto);
	}

	@Override()
	public List<T> getSelected(T bean, TableRequestDto tableRequestDto) {
		return this.getDao().getSelected(bean, tableRequestDto);
	}

	/**
	 * Devuelve los datos recuperados de la DB.
	 *
	 * @param filterUsuario   Usuario
	 * @param tableRequestDto TableRequestDto
	 */
	@Override
	public List<T> getDataForReports(T filter, TableRequestDto tableRequestDto) {
		if (null == filter) {
			T newFilter = null;
			try {
				newFilter = this.type.getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				LOGGER.error("getDataForReports", e);
			}
			return this.getDao().findAll(newFilter, tableRequestDto);
		} else {
			return this.getDao().findAll(filter, tableRequestDto);
		}

	}

}
