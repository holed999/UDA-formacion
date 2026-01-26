package com.ejie.zz99.common;

public class BooleanParser {

	private BooleanParser() {

	}

	public static Boolean fromString(String input) {
		if (input.equalsIgnoreCase("S")) {
			return true;
		} else if (input.equalsIgnoreCase("N")) {
			return false;
		} else {
			return null;
		}
	}

}
