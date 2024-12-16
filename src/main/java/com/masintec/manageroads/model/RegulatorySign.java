package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Sign.SignType.Values.REGULATORY_VALUE)
public class RegulatorySign extends Sign implements Serializable {

	private static final long serialVersionUID = 1005076596729777812L;

	public RegulatorySign() {
		super(SignType.REGULATORY);
	}

	public RegulatorySign(String name, String description, String side, Long point) {
		super(SignType.REGULATORY, name, description, side, point);
	}
}
