package com.masintec.manageroads.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue (value = Road.RoadType.Values.ASPHALT_VALUE)
public class AsphaltRoad extends Road implements Serializable {

	private static final long serialVersionUID = -7772543668778804173L;

	public AsphaltRoad() {
		super(Road.RoadType.ASPHALT);
	}
}
