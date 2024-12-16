package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Sign.SignType.Values.GUIDE_VALUE)
public class GuideSign extends Sign implements Serializable {

	private static final long serialVersionUID = -5772462064360720650L;

	public GuideSign() {
		super(SignType.GUIDE);
	}

	public GuideSign(SignType type, String name, String description, String side, Long point) {
		super(SignType.GUIDE, name, description, side, point);
	}
}
