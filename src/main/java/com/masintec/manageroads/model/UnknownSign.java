package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Sign.SignType.Values.UNKNOWN_VALUE)
public class UnknownSign extends Sign implements Serializable {

	private static final long serialVersionUID = -9090279255462754830L;

	public UnknownSign() {
		super(SignType.UNKNOWN);
	}

	public UnknownSign(String name, String description, String side, Long point) {
		super(SignType.UNKNOWN, name, description, side, point);
	}

}
