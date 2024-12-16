package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Road.RoadType.Values.GRAVEL_VALUE)
public class GravelRoad extends Road implements Serializable {

	private static final long serialVersionUID = -19025102065107524L;

	public GravelRoad() {
		super(Road.RoadType.GRAVEL);
	}
}
