package com.raffaelbrandao.demo.enums;

import lombok.Getter;

public enum Gender {
	F("Feminine"), M("Masculine");

	@Getter
	private String name;

	Gender(String name) {
		this.name = name;
	}
}
