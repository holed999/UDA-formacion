package com.ejie.zz99.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.ejie.zz99.model.enums.Situacion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer identificador;
	private String descEs;
	private String descEu;
	private Situacion situacion;
	private Date fecha;

	private String descFilter;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(Integer identificador) {
		super();
		this.setIdentificador(identificador);
	}

	public Mantenimiento(Situacion situacion) {
		super();
		this.setSituacion(situacion);
	}

	public Integer getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(Integer identificador) {
		if (identificador != null && identificador == 0) {
			// No existe la posibilidad de un ID 0, esto es NULL en BD.
			// En los resultSet.getInt siempre devuelve 0 cuando hay null
			identificador = null;
		}
		this.identificador = identificador;
	}

	public String getDescEs() {
		return this.descEs;
	}

	@JsonIgnore
	public String getDescEsSinNull() {
		return this.descEs == null ? "" : this.descEs;
	}

	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	public String getDescEu() {
		return this.descEu;
	}

	@JsonIgnore
	public String getDescEuSinNull() {
		return this.descEu == null ? "" : this.descEu;
	}

	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	public Situacion getSituacion() {
		return this.situacion;
	}

	public void setSituacion(Situacion situacion) {
		this.situacion = situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = Situacion.fromString(situacion);
	}

	public String getDescFilter() {
		return this.descFilter;
	}

	public void setDescFilter(String descFilter) {
		this.descFilter = descFilter;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFecha() {
		return fecha;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean comparandoObjetos(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mantenimiento other = (Mantenimiento) obj;
		return Objects.equals(identificador, other.identificador);
	}
}
