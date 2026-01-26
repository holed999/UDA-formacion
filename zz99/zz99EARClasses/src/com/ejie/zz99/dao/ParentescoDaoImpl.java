package com.ejie.zz99.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.zz99.model.Parentesco;
import com.ejie.zz99.model.ParentescoFilter;
import com.ejie.zz99.model.enums.Situacion;

@Repository
@Transactional
public class ParentescoDaoImpl extends GenericoDaoImpl<Parentesco> implements ParentescoDao {

	private static final String CPARENTESCO_02 = "CPARENTESCO_02";

	public ParentescoDaoImpl() {
		super(Parentesco.class, rwMap, rwMapPk, ORDER_BY_WHITE_LIST);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { CPARENTESCO_02, "DPARENTESCOC_02",
			"DPARENTESCOE_01", "SITUACION_01" };

	private static RowMapper<Parentesco> rwMap = new RowMapper<Parentesco>() {
		@Override
		public Parentesco mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Parentesco bean = new Parentesco();
			bean.setIdentificador(resultSet.getInt(CPARENTESCO_02));
			bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_02")));
			bean.setDescEs(resultSet.getString("DPARENTESCOC_02"));
			bean.setDescEu(resultSet.getString("DPARENTESCOE_02"));
			bean.setFecha(resultSet.getDate("FECHA_02"));
			return bean;
		}
	};

	private static RowMapper<Parentesco> rwMapPk = new RowMapper<Parentesco>() {
		@Override
		public Parentesco mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Parentesco bean = new Parentesco();
			bean.setIdentificador(resultSet.getInt(CPARENTESCO_02));
			return bean;
		}
	};

	@Override
	protected void getSelect(StringBuilder select) {
		select.append("SELECT  t1.CPARENTESCO_02");
		select.append(", t1.DPARENTESCOC_02");
		select.append(", t1.DPARENTESCOE_02");
		select.append(", t1.SITUACION_02");
		select.append(", t1.FECHA_02");
	}

	@Override
	protected void getFrom(StringBuilder from) {
		from.append(" FROM ZZ99_PARENTESCO t1");
	}

	@Override
	protected String getPK() {
		return CPARENTESCO_02;
	}

	@Override
	protected void getWherePK(StringBuilder wherePk, Parentesco bean, List<Object> params) {
		wherePk.append(" WHERE t1.CPARENTESCO_02 = ? ");
		params.add(bean.getIdentificador());
	}

	@Override
	protected void getWhere(StringBuilder where, Parentesco parentesco, List<Object> params, Boolean search) {
		where.append(this.generarWhereIgual("t1.CPARENTESCO_02", parentesco.getIdentificador(), params));
		where.append(this.generarWhereLike("t1.DPARENTESCOC_02", parentesco.getDescEs(), params));
		where.append(this.generarWhereLike("t1.DPARENTESCOE_02", parentesco.getDescEu(), params));
		where.append(
				this.generarWhereLike("t1.DPARENTESCOC_02", "t1.DPARENTESCOE_02", parentesco.getDescFilter(), params));

		if (null != parentesco.getSituacion()) {
			where.append(this.generarWhereIgual("t1.SITUACION_02", parentesco.getSituacion().getValue(), params));
		}

		if (parentesco instanceof ParentescoFilter) {
			ParentescoFilter aux = (ParentescoFilter) parentesco;

			where.append(this.generarWhereMayorIgualFecha("t1.FECHA_02", aux.getFechaDesde(), params));
			where.append(this.generarWhereMenorIgualFecha("t1.FECHA_02", aux.getFechaHasta(), params));
		}
	}

	@Override
	public Parentesco add(Parentesco parentesco) {
		StringBuilder query = new StringBuilder("INSERT INTO ZZ99_PARENTESCO ");
		query.append("(CPARENTESCO_02, DPARENTESCOC_02, DPARENTESCOE_02, SITUACION_02, FECHA_02) ");
		query.append("VALUES (?,?,?,?,?)");

		Integer id = this.getNextVal("ZZ9902Q00");
		this.getJdbcTemplate().update(query.toString(), id, parentesco.getDescEs(), parentesco.getDescEu(),
				parentesco.getSituacion().getValue(), parentesco.getFecha());
		parentesco.setIdentificador(id);
		return parentesco;
	}

	@Override
	public Parentesco update(Parentesco parentesco) {
		StringBuilder query = new StringBuilder("UPDATE ZZ99_PARENTESCO SET ");
		query.append("DPARENTESCOC_02 = ?");
		query.append(", DPARENTESCOE_02 = ? ");
		query.append(", SITUACION_02 = ? ");
		query.append(", FECHA_02 = ? ");
		query.append(" WHERE CPARENTESCO_02 = ? ");

		this.getJdbcTemplate().update(query.toString(), parentesco.getDescEs(), parentesco.getDescEu(),
				parentesco.getSituacion().getValue(), parentesco.getFecha(), parentesco.getIdentificador());
		return parentesco;
	}

	@Override
	public void remove(Parentesco parentesco) {
		String query = "DELETE FROM ZZ99_PARENTESCO WHERE CPARENTESCO_02=?";
		this.getJdbcTemplate().update(query, parentesco.getIdentificador());
	}

}
