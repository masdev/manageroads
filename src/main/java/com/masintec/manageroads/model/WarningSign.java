package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Sign.SignType.Values.WARNING_VALUE)
public class WarningSign extends Sign implements Serializable  {

	private static final long serialVersionUID = 8116343099289214965L;

	public WarningSign() {
		super(SignType.WARNING);
	}

	public WarningSign(String name, String description, String side, Long point) {
		super(SignType.WARNING, name, description, side, point);
	}
}
