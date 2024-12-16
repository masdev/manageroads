package com.masintec.manageroads.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Intersection;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.service.ManageRoadsService;

@RestController
public class ManageRoadsController {

	@Autowired
	private ManageRoadsService mrService;
	
	//---------- ROADS -----------
	
	@GetMapping (path = "/allroads", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Road> getAllRoads() {
		//System.out.println("getAllRoads");
		return mrService.getAllRoads();
	}

	@GetMapping (path = "/road/{id}")
	public Road getRoadById(@PathVariable Long id) throws DataNotFoundException {
		return mrService.getRoad(id);
	}
	
	/*
	 * JSON example
	 * {
     *	 "roadType": "CONCRETE",
     *	 "name": "Concrete road M316",
     *	 "description": "Simple concrete road"
	 * }
	 */
	@PutMapping (path = "/road", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Road addRoad(@RequestBody Road road) throws DataAlreadyExistsException {
		return mrService.addRoad(road);
	}

	/*
	 * JSON for update
	 * {
     *	"roadType": "CONCRETE",
     *	"id": 4,
     *	"name": "Concrete road M3190",
     *	"description": "Simple concrete road"
	 *	}
	 */
	@PostMapping (path = "/road", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Road updateRoad(@RequestBody Road road) throws DataNotFoundException {
		return mrService.updateRoad(road);
	}
	
	@DeleteMapping (path = "/road/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteRoad(@PathVariable Long id) throws DataNotFoundException {
		mrService.deleteRoad(id);
	}
	
	//-------- SIGNS ------------
	
	@GetMapping (path = "/allsigns", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Sign> getAllSigns() {
		//System.out.println("getAllSigns");
		return mrService.getAllSigns();
	}

	@GetMapping (path = "/sign/{id}")
	public Sign getSignById(@PathVariable Long id) throws DataNotFoundException {
		return mrService.getSign(id);
	}
	
	
	/*
	 * {
        "signType": "WARNING",
        "id": 1500,
        "name": "Caution: falling stones",
        "description": "Simple sign",
        "side": "RIGHT",
        "point": 2245
    }
	 */

	@PutMapping (path = "/sign", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Sign addSign(@RequestBody Sign sign) throws DataAlreadyExistsException {
		return mrService.addSign(sign);
	}

	/*
	 * {
     *   "signType": "REGULATORY",
     *   "id": 3000,
     *   "road": {
     *       "roadType": "GRAVEL",
     *       "id": 1000,
     *       "name": "Gravel road M50",
     *       "description": "Simple road"
     *   },
     *   "name": "No Parking",
     *   "description": "Simple sign",
     *   "side": "RIGHT",
     *   "point": 5641
     * }
	 */
	@PostMapping (path = "/sign", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Sign updateSign(@RequestBody Sign sign) throws DataNotFoundException {
		return mrService.updateSign(sign);
	}
	
	@DeleteMapping (path = "/sign/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteSign(@PathVariable Long id) throws DataNotFoundException {
		mrService.deleteSign(id);
	}
	
	//-------- INTERSECTIONS ------------
	
	@GetMapping (path = "/allintersections", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Intersection> getAllIntersections() {
		//System.out.println("getAllIntersections");
		return mrService.getAllIntersections();
	}

	@GetMapping (path = "/intersection/{id}")
	public Intersection getIntersectionById(@PathVariable Long id) throws DataNotFoundException {
		return mrService.getIntersection(id);
	}

	/*
	 * {
     *	"name": "Simple 3 intersection"
	 * }
	 */
	@PutMapping (path = "/intersection", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Intersection addIntersection(@RequestBody Intersection intersection) throws DataAlreadyExistsException {
		return mrService.addIntersection(intersection);
	}

	/*
	 * {
     * "id": 1,
     * "name": "Simple 1 intersection"
	 * }
	 */
	@PostMapping (path = "/intersection", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Intersection updateIntersection(@RequestBody Intersection intersection) throws DataNotFoundException {
		return mrService.updateIntersection(intersection);
	}

	@DeleteMapping (path = "/intersection/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteIntersection(@PathVariable Long id) throws DataNotFoundException {
		mrService.deleteIntersection(id);
	}

	// ------- Bind Intersection with Road -----------
	/*
	 * {
	    "intersection": {
	        "id": 3000,
	        "name": "Simple intersection"
	    },
	    "roads": [{
			        "roadType": "GRAVEL",
			        "id": 1000,
			        "intersections": [],
			        "name": "Gravel road M50",
			        "description": "Simple road"
			    },
			    {
			        "roadType": "ASPHALT",
			        "id": 2000,
			        "intersections": [],
			        "name": "Asphalt road M10",
			        "description": "Simple road"
			    }
		    ]
		}
	 */
	@PostMapping (path = "/intersectionroad", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void bindIntersectionRoad(@RequestBody ObjectNode objectNode) throws DataNotFoundException, JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode intersectionJson = objectNode.findPath("intersection");
		Intersection intersection = mapper.convertValue(intersectionJson, Intersection.class);

		JsonNode roadsNode = objectNode.findPath("roads");
		ArrayNode arrayNode = mapper.createArrayNode();

		// fill arrayNode with values from roadsNode
		roadsNode.elements().forEachRemaining(arrayNode::add);

		Set<Road> roads = mapper.convertValue(arrayNode, mapper.getTypeFactory().constructCollectionType(Set.class, Road.class));

		mrService.updateCreateIntersectionRoadBind(intersection, roads);
	}

	@DeleteMapping (path = "/intersectionroad/{intersectionId}/{roadId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteIntersectionRoadBind(@PathVariable Long intersectionId, @PathVariable Long roadId) throws DataNotFoundException {
		mrService.deleteIntersectionRoadBind(intersectionId, roadId);
	}

	// ------- Bind Sign with Road -------------------
	/*
	 * {
    "sign": {
        "signType": "WARNING",
        "id": 1000,
        "name": "Caution: falling stones",
        "description": "Simple sign",
        "side": "RIGHT",
        "point": 2245
    },
    "road": {
			"roadType": "GRAVEL",
			"id": 1000,
			"intersections": [],
			"name": "Gravel road M50",
			"description": "Simple road"
	}
}
	 */
	
	@PostMapping (path = "/roadsign", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void bindRoadSign(@RequestBody ObjectNode objectNode) throws DataNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode signJson = objectNode.findPath("sign");
		JsonNode roadJson = objectNode.findPath("road");

		Sign sign = mapper.convertValue(signJson, Sign.class);
		Road road = mapper.convertValue(roadJson, Road.class);

		mrService.updateCreateRoadSignBind(road, sign);
	}

	@DeleteMapping (path = "/roadsign", produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteRoadSignBind(@PathVariable Long roadId, @PathVariable Long signId) throws DataNotFoundException {
		mrService.deleteRoadSignBind(roadId, signId);
	}
}
