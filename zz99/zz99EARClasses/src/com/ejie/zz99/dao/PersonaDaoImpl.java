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

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { ID_Persona, "NOMBRE_03", "APELLIDO1_03",
			"APELLIDO2_03", "FECHA_NACIMIENTO_03", "TLFONO_03", "PROVINCIA_03", "MUNICIPIO_03", "CP_03",
			"DIRECCION_03" };

	private static RowMapper<Persona> rwMap = new RowMapper<Persona>() {
		@Override
		public Persona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Persona bean = new Persona();
			bean.setIdentificador(resultSet.getInt(ID_Persona));
			bean.setNombre(resultSet.getString("NOMBRE_03"));
			bean.setApellido1(resultSet.getString("APELLIDO1_03"));
			bean.setApellido2(resultSet.getString("APELLIDO2_03"));
			bean.setFechaNacimiento(resultSet.getDate("FECHA_NACIMIENTO_03"));
			bean.setTelefono(resultSet.getString("TLFONO_03"));
			bean.setProvincia(resultSet.getInt("PROVINCIA_03"));
			bean.setMunicipio(resultSet.getInt("MUNICIPIO_03"));
			bean.setCodigoPostal(resultSet.getString("CP_03"));
			bean.setDireccion(resultSet.getString("DIRECCION_03"));
			// Si Persona tiene campo situacion, se puede mapear si existe en la tabla
			// bean.setSituacion(Situacion.fromString(resultSet.getString("SITUACION_03")));
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
		select.append("SELECT  t1.ID_03");
		select.append(", t1.NOMBRE_03");
		select.append(", t1.APELLIDO1_03");
		select.append(", t1.APELLIDO2_03");
		select.append(", t1.FECHA_NACIMIENTO_03");
		select.append(", t1.TLFONO_03");
		select.append(", t1.PROVINCIA_03");
		select.append(", t1.MUNICIPIO_03");
		select.append(", t1.CP_03");
		select.append(", t1.DIRECCION_03");
	}

	@Override
	protected void getFrom(StringBuilder from) {
		from.append(" FROM ZZ99_PERSONA_03 t1");
	}

	@Override
	protected String getPK() {
		return ID_Persona;
	}

	@Override
	protected void getWherePK(StringBuilder wherePk, Persona bean, List<Object> params) {
		wherePk.append(" WHERE t1.ID_03 = ? ");
		params.add(bean.getIdentificador());
	}

	@Override
	protected void getWhere(StringBuilder where, Persona persona, List<Object> params, Boolean search) {
		where.append(this.generarWhereIgual("t1.ID_03", persona.getIdentificador(), params));
		where.append(this.generarWhereLike("t1.NOMBRE_03", persona.getNombre(), params));
		where.append(this.generarWhereLike("t1.APELLIDO1_03", persona.getApellido1(), params));
		where.append(this.generarWhereLike("t1.APELLIDO2_03", persona.getApellido2(), params));
		where.append(this.generarWhereLike("t1.TLFONO_03", persona.getTelefono(), params));
		where.append(this.generarWhereIgual("t1.PROVINCIA_03", persona.getProvincia(), params));
		where.append(this.generarWhereIgual("t1.MUNICIPIO_03", persona.getMunicipio(), params));
		where.append(this.generarWhereLike("t1.CP_03", persona.getCodigoPostal(), params));
		where.append(this.generarWhereLike("t1.DIRECCION_03", persona.getDireccion(), params));

		// Si quieres buscar por nombre o apellidos juntos, puedes añadir algo como:
		// where.append(this.generarWhereLike("t1.NOMBRE_03", "t1.APELLIDO1_03",
		// "t1.APELLIDO2_03", persona.getNombreCompleto(), params));

		if (persona instanceof PersonaFilter) {
			PersonaFilter aux = (PersonaFilter) persona;
			where.append(this.generarWhereMayorIgualFecha("t1.FECHA_NACIMIENTO_03", aux.getFechaDesde(), params));
			where.append(this.generarWhereMenorIgualFecha("t1.FECHA_NACIMIENTO_03", aux.getFechaHasta(), params));
		}
	}

	@Override
	public Persona add(Persona persona) {
		StringBuilder query = new StringBuilder("INSERT INTO ZZ99_PERSONA_03 ");
		query.append("(ID_03, NOMBRE_03, APELLIDO1_03, APELLIDO2_03, FECHA_NACIMIENTO_03, ");
		query.append("TLFONO_03, PROVINCIA_03, MUNICIPIO_03, CP_03, DIRECCION_03) ");
		query.append("VALUES (?,?,?,?,?,?,?,?,?,?)");

		Integer id = this.getNextVal("ZZ9903T00");
		this.getJdbcTemplate().update(query.toString(), id, persona.getNombre(), persona.getApellido1(),
				persona.getApellido2(), persona.getFechaNacimiento(), persona.getTelefono(), persona.getProvincia(),
				persona.getMunicipio(), persona.getCodigoPostal(), persona.getDireccion());
		persona.setIdentificador(id);
		return persona;
	}

	@Override
	public Persona update(Persona persona) {
		StringBuilder query = new StringBuilder("UPDATE ZZ99_PERSONA_03 SET ");
		query.append("NOMBRE_03 = ?");
		query.append(", APELLIDO1_03 = ? ");
		query.append(", APELLIDO2_03 = ? ");
		query.append(", FECHA_NACIMIENTO_03 = ? ");
		query.append(", TLFONO_03 = ? ");
		query.append(", PROVINCIA_03 = ? ");
		query.append(", MUNICIPIO_03 = ? ");
		query.append(", CP_03 = ? ");
		query.append(", DIRECCION_03 = ? ");
		query.append(" WHERE ID_03 = ? ");

		this.getJdbcTemplate().update(query.toString(), persona.getNombre(), persona.getApellido1(),
				persona.getApellido2(), persona.getFechaNacimiento(), persona.getTelefono(), persona.getProvincia(),
				persona.getMunicipio(), persona.getCodigoPostal(), persona.getDireccion(), persona.getIdentificador());
		return persona;
	}

	@Override
	public void remove(Persona persona) {
		String query = "DELETE FROM ZZ99_PERSONA_03 WHERE ID_03=?";
		this.getJdbcTemplate().update(query, persona.getIdentificador());
	}
}