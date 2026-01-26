package com.ejie.zz99.model;

import java.util.Date;

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

}
