package com.ejie.zz99.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.zz99.model.Persona;
import com.ejie.zz99.model.PersonaFilter;
import com.ejie.zz99.model.enums.Situacion;

@Repository
@Transactional
public class PersonaDaoImpl extends GenericoDaoImpl<Persona> implements PersonaDao {

	private static final String ID_Persona = "ID_03";

	public PersonaDaoImpl() {
		super(Persona.class, rwMap, rwMapPk, ORDER_BY_WHITE_LIST);
	}

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ID_Persona, "DPERSONAC_02", "DPERSONAE_01",
			"SITUACION_01" };

	private static RowMapper<Persona> rwMap = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Persona bean = new Persona();
			bean.setIdentificador(resultSet.getInt(ID_Persona));
			bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_02")));
			bean.setDescEs(resultSet.getString("DPERSONAC_02"));
			bean.setDescEu(resultSet.getString("DPERSONAE_02"));
			bean.setFecha(resultSet.getDate("FECHA_02"));
			bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_02")));
			bean.setDescEs(resultSet.getString("DPERSONAC_02"));
			bean.setDescEu(resultSet.getString("DPERSONAE_02"));
			bean.setFecha(resultSet.getDate("FECHA_02"));
			bean.setFecha(resultSet.getDate("FECHA_02"));
			return bean;
		}
	};

	private static RowMapper<Persona> rwMapPk = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Persona bean = new Persona();
			bean.setIdentificador(resultSet.getInt(ID_Persona));
			return bean;
		}
	};

	@Override
	protected void getSelect(StringBuilder select) {
		select.append("SELECT  t1.CPERSONA_02");
		select.append(", t1.DPERSONAC_02");
		select.append(", t1.DPERSONAE_02");
		select.append(", t1.SITUACION_02");
		select.append(", t1.FECHA_02");
	}

	@Override
	protected void getFrom(StringBuilder from) {
		from.append(" FROM ZZ99_PERSONA t1");
	}

	@Override
	protected String getPK() {
		return ID_Persona;
	}

	@Override
	protected void getWherePK(StringBuilder wherePk, Persona bean, List<Object> params) {
		wherePk.append(" WHERE t1.CPERSONA_02 = ? ");
		params.add(bean.getIdentificador());
	}

	@Override
	protected void getWhere(StringBuilder where, Persona persona, List<Object> params, Boolean search) {
		where.append(this.generarWhereIgual("t1.CPERSONA_02", persona.getIdentificador(), params));
		where.append(this.generarWhereLike("t1.DPERSONAC_02", persona.getDescEs(), params));
		where.append(this.generarWhereLike("t1.DPERSONAE_02", persona.getDescEu(), params));
		where.append(this.generarWhereLike("t1.DPERSONAC_02", "t1.DPERSONAE_02", persona.getDescFilter(), params));

		if (null != persona.getSituacion()) {
			where.append(this.generarWhereIgual("t1.SITUACION_02", persona.getSituacion().getValue(), params));
		}

		if (persona instanceof PersonaFilter) {
			PersonaFilter aux = (PersonaFilter) persona;

			where.append(this.generarWhereMayorIgualFecha("t1.FECHA_02", aux.getFechaDesde(), params));
			where.append(this.generarWhereMenorIgualFecha("t1.FECHA_02", aux.getFechaHasta(), params));
		}
	}

	@Override
	public Persona add(Persona persona) {
		StringBuilder query = new StringBuilder("INSERT INTO ZZ99_PERSONA ");
		query.append("(CPERSONA_02, DPERSONAC_02, DPERSONAE_02, SITUACION_02, FECHA_02) ");
		query.append("VALUES (?,?,?,?,?)");

		Integer id = this.getNextVal("ZZ9903Q00");
		this.getJdbcTemplate().update(query.toString(), id, persona.getDescEs(), persona.getDescEu(),
				persona.getSituacion().getValue(), persona.getFecha());
		persona.setIdentificador(id);
		return persona;
	}

	@Override
	public Persona update(Persona persona) {
		StringBuilder query = new StringBuilder("UPDATE ZZ99_PERSONA SET ");
		query.append("DPERSONAC_02 = ?");
		query.append(", DPERSONAE_02 = ? ");
		query.append(", SITUACION_02 = ? ");
		query.append(", FECHA_02 = ? ");
		query.append(" WHERE CPERSONA_02 = ? ");

		this.getJdbcTemplate().update(query.toString(), persona.getDescEs(), persona.getDescEu(),
				persona.getSituacion().getValue(), persona.getFecha(), persona.getIdentificador());
		return persona;
	}

	@Override
	public void remove(Persona persona) {
		String query = "DELETE FROM ZZ99_PERSONA WHERE CPERSONA_02=?";
		this.getJdbcTemplate().update(query, persona.getIdentificador());
	}

}
