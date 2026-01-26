package com.ejie.zz99.model;

import java.io.Serializable;

import com.ejie.zz99.model.enums.Situacion;

public class Parentesco extends Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Parentesco() {
		super();
	}

	public Parentesco(Situacion situacion) {
		super(situacion);
	}

	public Parentesco(String identificador) {
		super();
		if (null != identificador) {
			this.setIdentificador(Integer.valueOf(identificador));
		} else {
			this.setIdentificador(0);
		}

	}

	public Parentesco(Integer identificador) {
		super(identificador);
	}

	public Parentesco(Integer identificador, String descEs, String descEu) {
		super(identificador);
		this.setDescEs(descEs);
		this.setDescEu(descEu);
	}

}
