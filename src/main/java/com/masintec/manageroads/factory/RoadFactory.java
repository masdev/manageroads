package com.masintec.manageroads.factory;

import org.springframework.stereotype.Component;

import com.masintec.manageroads.model.AsphaltRoad;
import com.masintec.manageroads.model.ConcreteRoad;
import com.masintec.manageroads.model.GravelRoad;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.model.UnknownRoad;
import com.masintec.manageroads.model.Road.RoadType;

public class RoadFactory {

	public static Road createRoad(Road road) {
		Road created = null;

		switch (road.getRoadType().getRType()) {
			case RoadType.Values.GRAVEL_VALUE:
				created = new GravelRoad();
				break;
			case RoadType.Values.ASPHALT_VALUE:
				created = new AsphaltRoad();
				break;
			case RoadType.Values.CONCRETE_VALUE:
				created = new ConcreteRoad();
				break;
			default:
				created = new UnknownRoad();
				break;
		}
		
		// map values
		created.setId(road.getId());
		created.setName(road.getName());
		created.setDescription(road.getDescription());
		
		return created;
	}
}
