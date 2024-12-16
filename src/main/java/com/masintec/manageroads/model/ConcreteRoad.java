package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Road.RoadType.Values.CONCRETE_VALUE)
public class ConcreteRoad extends Road implements Serializable {

	private static final long serialVersionUID = 3089371225352068192L;

	public ConcreteRoad() {
		super(Road.RoadType.CONCRETE);
	}
}
