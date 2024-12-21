package com.masintec.manageroads.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.factory.IntersectionFactory;
import com.masintec.manageroads.model.Intersection;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.repository.IntersectionRepository;
import com.masintec.manageroads.repository.RoadRepository;

@Service
public class IntersectionServiceImpl implements IntersectionService {

	@Autowired
	private IntersectionRepository interRepo;

	@Autowired
	private RoadRepository roadRepo;

	@Override
	public List<Intersection> getAllIntersections() {
		return interRepo.findAll();
	}

	@Override
	public Intersection getIntersection(Long id) throws DataNotFoundException {
		return interRepo.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public Intersection addIntersection(Intersection intersection) throws DataAlreadyExistsException {
		if (intersection.getId() != null && interRepo.findById(intersection.getId()).isPresent()) {
			throw new DataAlreadyExistsException();
		}

		intersection = IntersectionFactory.createIntersection(intersection);

		return interRepo.save(intersection);
	}

	@Override
	public Intersection updateIntersection(Intersection intersection) throws DataNotFoundException {
		if (intersection.getId() == null || interRepo.findById(intersection.getId()).isEmpty()) {
			throw new DataNotFoundException();
		}

		intersection = IntersectionFactory.createIntersection(intersection);

		return interRepo.save(intersection);
	}

	@Override
	public void deleteIntersection(Long id) throws DataNotFoundException {
		if (id == null || interRepo.findById(id).isEmpty()) {
			throw new DataNotFoundException();
		}

		interRepo.deleteById(id);
	}
	
	@Override
	public void updateCreateIntersectionRoadBind(Long intersectionId, Set<Long> roadIds) throws DataNotFoundException {
		if (intersectionId == null || roadIds == null || roadIds.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Optional <Intersection> intersectionOpt = interRepo.findById(intersectionId);
		if (intersectionOpt.isEmpty()) {
			throw new DataNotFoundException();
		}

		Intersection intersection = intersectionOpt.get();
		Set<Road> intersectionRoads = intersection.getRoads();
		if (intersectionRoads == null) {
			intersectionRoads = new HashSet<Road>();
			intersection.setRoads(intersectionRoads);
		}

		for (Long roadId : roadIds) {
			if (roadId == null) {
				throw new IllegalArgumentException();
			}

			Optional <Road> roadOpt = roadRepo.findById(roadId);
			if (roadOpt.isEmpty()) {
				throw new DataNotFoundException();
			}
			Road road = roadOpt.get();

			intersectionRoads.add(road);
		}

		interRepo.save(intersection);
	}

	@Override
	public void deleteIntersectionRoadBind(Long intersectionId, Long roadId) throws DataNotFoundException {
		if (intersectionId == null || roadId == null) {
			throw new IllegalArgumentException();
		}

		Optional <Intersection> intersectionOpt = interRepo.findById(intersectionId);
		if (intersectionOpt.isEmpty()) {
			throw new DataNotFoundException();
		}
		Intersection intersection = intersectionOpt.get();

		Optional <Road> roadOpt = roadRepo.findById(roadId);
		if (roadOpt.isEmpty()) {
			throw new DataNotFoundException();
		}

		intersection.getRoads().remove(roadOpt.get());
		interRepo.save(intersection);
	}
}
