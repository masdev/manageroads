package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Road.RoadType.Values.UNKNOWN_VALUE)
public class UnknownRoad extends Road implements Serializable  {

	private static final long serialVersionUID = 130457828835183252L;

	public UnknownRoad() {
		super(Road.RoadType.UNKNOWN);
	}
}
