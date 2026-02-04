package com.ejie.zz99.model;

import java.sql.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PersonaFilter extends Persona {

	private static final long serialVersionUID = 1L;

	private Date fechaDesde;
	private Date fechaHasta;

	public PersonaFilter() {
		super();
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesde() {
		return fechaDesde;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHasta() {
		return fechaHasta;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	// También puedes añadir métodos específicos para filtrar por fecha de
	// nacimiento
	// si quieres mantener coherencia con el nombre del campo en Persona
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaNacimientoDesde() {
		return fechaDesde;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaNacimientoDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaNacimientoHasta() {
		return fechaHasta;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaNacimientoHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}