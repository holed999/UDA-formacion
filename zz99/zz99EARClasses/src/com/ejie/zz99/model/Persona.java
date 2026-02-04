package com.ejie.zz99.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.zz99.model.enums.Situacion;

public class Persona extends Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaNacimiento;
	private String telefono;
	private Integer provincia;
	private Integer municipio;
	private String codigoPostal;
	private String direccion;

	public Persona() {
		super();
	}

	public Persona(Situacion situacion) {
		super(situacion);
	}

	public Persona(String identificador) {
		super();
		if (null != identificador) {
			this.setIdentificador(Integer.valueOf(identificador));
		} else {
			this.setIdentificador(0);
		}
	}

	public Persona(Integer identificador) {
		super(identificador);
	}

	public Persona(Integer identificador, String nombre, String apellido1, String apellido2) {
		super(identificador);
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// Métodos útiles
	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder();
		if (nombre != null)
			sb.append(nombre);
		if (apellido1 != null) {
			if (sb.length() > 0)
				sb.append(" ");
			sb.append(apellido1);
		}
		if (apellido2 != null) {
			if (sb.length() > 0)
				sb.append(" ");
			sb.append(apellido2);
		}
		return sb.toString();
	}
}