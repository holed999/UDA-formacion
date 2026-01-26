package com.ejie.zz99.model.enums;

public class SituacionCombo {

	private String id;
	private String value; // se necesita para que el combo funcione. Bug de UDA
	private String valueEs;
	private String valueEu;

	/**
	 *
	 * @param value String
	 */
	public SituacionCombo(String id, String valueEs, String valueEu) {
		this.id = id;
		this.value = id;
		this.valueEs = valueEs;
		this.valueEu = valueEu;
	}

	/**
	 * @return the value
	 */
	public String getValueEs() {
		return this.valueEs;
	}

	public String getValueEu() {
		return this.valueEu;
	}

	public String getValue() {
		return this.value;
	}

	public String getId() {
		return this.id;
	}

}
