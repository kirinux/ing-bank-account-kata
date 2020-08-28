package com.kata.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActionType {
	 DEPOSIT("deposit"),
	 WITHDRAW("withdraw");

	private final String name;
	ActionType(String name) {
		this.name = name;
	}
	
	@Override
	@JsonValue
	public String toString() {
		return name;
	}
}
