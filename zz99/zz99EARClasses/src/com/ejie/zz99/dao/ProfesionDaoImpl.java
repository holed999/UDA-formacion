package com.ejie.zz99.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.zz99.model.Profesion;
import com.ejie.zz99.model.ProfesionFilter;
import com.ejie.zz99.model.enums.Situacion;

@Repository
@Transactional
public class ProfesionDaoImpl extends GenericoDaoImpl<Profesion> implements ProfesionDao {

	private static final String CPROFESION_01 = "CPROFESION_01";

	public ProfesionDaoImpl() {
		super(Profesion.class, rwMap, rwMapPk, ORDER_BY_WHITE_LIST);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { CPROFESION_01, "DPROFESIONC_01",
			"DPROFESIONE_01", "SITUACION_01" };

	private static RowMapper<Profesion> rwMap = new RowMapper<Profesion>() {
		@Override
		public Profesion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Profesion bean = new Profesion();
			bean.setIdentificador(resultSet.getInt(CPROFESION_01));
			bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_01")));
			bean.setDescEs(resultSet.getString("DPROFESIONC_01"));
			bean.setDescEu(resultSet.getString("DPROFESIONE_01"));
			bean.setFecha(resultSet.getDate("FECHA_01"));
			return bean;
		}
	};

	private static RowMapper<Profesion> rwMapPk = new RowMapper<Profesion>() {
		@Override
		public Profesion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Profesion bean = new Profesion();
			bean.setIdentificador(resultSet.getInt(CPROFESION_01));
			return bean;
		}
	};

	@Override
	protected void getSelect(StringBuilder select) {
		select.append("SELECT  t1.CPROFESION_01");
		select.append(", t1.DPROFESIONC_01");
		select.append(", t1.DPROFESIONE_01");
		select.append(", t1.SITUACION_01");
		select.append(", t1.FECHA_01");
	}

	@Override
	protected void getFrom(StringBuilder from) {
		from.append(" FROM ZZ99_PROFESION t1");
	}

	@Override
	protected String getPK() {
		return CPROFESION_01;
	}

	@Override
	protected void getWherePK(StringBuilder wherePk, Profesion bean, List<Object> params) {
		wherePk.append(" WHERE t1.CPROFESION_01 = ? ");
		params.add(bean.getIdentificador());
	}

	@Override
	protected void getWhere(StringBuilder where, Profesion profesion, List<Object> params, Boolean search) {
		where.append(this.generarWhereIgual("t1.CPROFESION_01", profesion.getIdentificador(), params));
		where.append(this.generarWhereLike("t1.DPROFESIONC_01", profesion.getDescEs(), params));
		where.append(this.generarWhereLike("t1.DPROFESIONE_01", profesion.getDescEu(), params));
		where.append(
				this.generarWhereLike("t1.DPROFESIONC_01", "t1.DPROFESIONE_01", profesion.getDescFilter(), params));

		if (null != profesion.getSituacion()) {
			where.append(this.generarWhereIgual("t1.SITUACION_01", profesion.getSituacion().getValue(), params));
		}

		if (profesion instanceof ProfesionFilter) {
			ProfesionFilter aux = (ProfesionFilter) profesion;

			where.append(this.generarWhereMayorIgualFecha("t1.FECHA_01", aux.getFechaDesde(), params));
			where.append(this.generarWhereMenorIgualFecha("t1.FECHA_01", aux.getFechaHasta(), params));
		}
	}

	@Override
	public Profesion add(Profesion profesion) {
		StringBuilder query = new StringBuilder("INSERT INTO ZZ99_PROFESION ");
		query.append("(CPROFESION_01, DPROFESIONC_01, DPROFESIONE_01, SITUACION_01, FECHA_01) ");
		query.append("VALUES (?,?,?,?,?)");

		Integer id = this.getNextVal("ZZ9901Q00");
		this.getJdbcTemplate().update(query.toString(), id, profesion.getDescEs(), profesion.getDescEu(),
				profesion.getSituacion().getValue(), profesion.getFecha());
		profesion.setIdentificador(id);
		return profesion;
	}

	@Override
	public Profesion update(Profesion profesion) {
		StringBuilder query = new StringBuilder("UPDATE ZZ99_PROFESION SET ");
		query.append("DPROFESIONC_01 = ?");
		query.append(", DPROFESIONE_01 = ? ");
		query.append(", SITUACION_01 = ? ");
		query.append(", FECHA_01 = ? ");
		query.append(" WHERE CPROFESION_01 = ? ");

		this.getJdbcTemplate().update(query.toString(), profesion.getDescEs(), profesion.getDescEu(),
				profesion.getSituacion().getValue(), profesion.getFecha(), profesion.getIdentificador());
		return profesion;
	}

	@Override
	public void remove(Profesion profesion) {
		String query = "DELETE FROM ZZ99_PROFESION WHERE CPROFESION_01=?";
		this.getJdbcTemplate().update(query, profesion.getIdentificador());
	}

}
