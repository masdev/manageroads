package com.masintec.manageroads.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.masintec.manageroads.model.Intersection;
import com.masintec.manageroads.service.IntersectionService;

@RestController
public class IntersectionController {

	@Autowired
	private IntersectionService iService;

	@GetMapping(path = "/intersections", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Intersection> getAllIntersections() {
		// System.out.println("getAllIntersections");
		return iService.getAllIntersections();
	}

	@GetMapping(path = "/intersection/{id}")
	public Intersection getIntersectionById(@PathVariable Long id) throws DataNotFoundException {
		return iService.getIntersection(id);
	}

	/*
	 * { "name": "Simple 3 intersection" }
	 */
	@PutMapping(path = "/intersections", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Intersection addIntersection(@RequestBody Intersection intersection) throws DataAlreadyExistsException {
		return iService.addIntersection(intersection);
	}

	/*
	 * { "id": 1, "name": "Simple 1 intersection" }
	 */
	@PostMapping(path = "/intersections", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Intersection updateIntersection(@RequestBody Intersection intersection) throws DataNotFoundException {
		return iService.updateIntersection(intersection);
	}

	@DeleteMapping(path = "/intersections/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteIntersection(@PathVariable Long id) throws DataNotFoundException {
		iService.deleteIntersection(id);
	}

	// ------- Bind Intersection with Road -----------
	@PostMapping(path = "/intersections/{intersectionId}/roads/{roadIds}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void bindIntersectionRoad(@PathVariable Long intersectionId, @PathVariable String roadIdsStr)
			throws DataNotFoundException {
		if (roadIdsStr == null || roadIdsStr.isBlank()) {
			throw new IllegalArgumentException();
		}

		Set<Long> roadIds = Stream.of(roadIdsStr.split(",")).map(Long::valueOf).collect(Collectors.toSet());

		iService.updateCreateIntersectionRoadBind(intersectionId, roadIds);
	}

	@DeleteMapping(path = "/intersections/{intersectionId}/roads/{roadId}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void deleteIntersectionRoadBind(@PathVariable Long intersectionId, @PathVariable Long roadId)
			throws DataNotFoundException {
		iService.deleteIntersectionRoadBind(intersectionId, roadId);
	}
}
