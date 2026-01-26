package com.ejie.zz99.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Situacion {

	ALTA("A"), BAJA("B");

	private String value;

	/**
	 *
	 * @param value String
	 */
	private Situacion(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	@JsonValue
	public String getValue() {
		return this.value;
	}

	public static Situacion fromString(String dato) {
		for (Situacion situacion : Situacion.values()) {
			if (situacion.value.equalsIgnoreCase(dato)) {
				return situacion;
			}
		}
		return null;
	}

}
