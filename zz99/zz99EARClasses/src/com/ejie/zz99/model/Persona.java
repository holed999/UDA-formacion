package com.ejie.zz99.model;

import java.io.Serializable;

import com.ejie.zz99.model.enums.Situacion;

public class Persona extends Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public Persona(Integer identificador, String descEs, String descEu) {
		super(identificador);
		this.setDescEs(descEs);
		this.setDescEu(descEu);
	}

}
