package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Sign.SignType.Values.INFORMATION_VALUE)
public class InformationSign extends Sign implements Serializable {

	private static final long serialVersionUID = 8056112451997495630L;

	public InformationSign() {
		super(SignType.INFORMATION);
	}

	public InformationSign(String name, String description, String side, Long point) {
		super(SignType.INFORMATION, name, description, side, point);
	}
}
