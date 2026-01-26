package com.ejie.zz99.model;

import java.io.Serializable;

import com.ejie.zz99.model.enums.Situacion;

public class Profesion extends Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Profesion() {
		super();
	}

	public Profesion(Situacion situacion) {
		super(situacion);
	}

	public Profesion(String identificador) {
		super();
		if (null != identificador) {
			this.setIdentificador(Integer.valueOf(identificador));
		} else {
			this.setIdentificador(0);
		}

	}

	public Profesion(Integer identificador) {
		super(identificador);
	}

	public Profesion(Integer identificador, String descEs, String descEu) {
		super(identificador);
		this.setDescEs(descEs);
		this.setDescEu(descEu);
	}

}
