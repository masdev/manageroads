package com.masintec.manageroads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.service.RoadService;

@RestController
public class RoadController {

	@Autowired
	private RoadService rService;

	@GetMapping(path = "/roads", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Road> getAllRoads() {
		// System.out.println("getAllRoads");
		return rService.getAllRoads();
	}

	@GetMapping(path = "/roads/{id}")
	public Road getRoadById(@PathVariable Long id) throws DataNotFoundException {
		return rService.getRoad(id);
	}

	/*
	 * JSON example { "roadType": "CONCRETE", "name": "Concrete road M316",
	 * "description": "Simple concrete road" }
	 */
	@PutMapping(path = "/roads", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Road addRoad(@RequestBody Road road) throws DataAlreadyExistsException {
		return rService.addRoad(road);
	}

	/*
	 * JSON for update { "roadType": "CONCRETE", "id": 4, "name":
	 * "Concrete road M3190", "description": "Simple concrete road" }
	 */
	@PostMapping(path = "/roads", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Road updateRoad(@RequestBody Road road) throws DataNotFoundException {
		return rService.updateRoad(road);
	}

	@DeleteMapping(path = "/roads/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteRoad(@PathVariable Long id) throws DataNotFoundException {
		rService.deleteRoad(id);
	}

	// ------- Bind Sign with Road -------------------
	@PostMapping(path = "/roads/{roadId}/sign/{signId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void bindRoadSign(@PathVariable Long roadId, @PathVariable Long signId) throws DataNotFoundException {
		rService.updateCreateRoadSignBind(roadId, signId);
	}

	@DeleteMapping(path = "/roads/{roadId}/sign/{signId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteRoadSignBind(@PathVariable Long roadId, @PathVariable Long signId) throws DataNotFoundException {
		rService.deleteRoadSignBind(roadId, signId);
	}
}
