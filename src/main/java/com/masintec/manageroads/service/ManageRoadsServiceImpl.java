package com.masintec.manageroads.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.factory.IntersectionFactory;
import com.masintec.manageroads.factory.RoadFactory;
import com.masintec.manageroads.factory.SignFactory;
import com.masintec.manageroads.model.Intersection;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.repository.IntersectionRepository;
import com.masintec.manageroads.repository.RoadRepository;
import com.masintec.manageroads.repository.SignRepository;

@Service
public class ManageRoadsServiceImpl implements ManageRoadsService {

	@Autowired
	private RoadRepository roadRepo;

	@Autowired
	private SignRepository signRepo;

	@Autowired
	private IntersectionRepository interRepo;

	public ManageRoadsServiceImpl() {
	}

	@Override
	public List<Road> getAllRoads() {
		List<Road> result = roadRepo.findAll();
		return result;
	}

	@Override
	public Road getRoad(Long id) throws DataNotFoundException {
		return roadRepo.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public Road addRoad(Road road) throws DataAlreadyExistsException {
		if (road.getId() != null) {
			Road roadToSave = roadRepo.findById(road.getId()).orElseGet(() -> null);
			if (roadToSave != null) {
				throw new DataAlreadyExistsException();
			}
		}
		
		road = RoadFactory.createRoad(road);

		return roadRepo.save(road);
	}
	
	@Override
	public Road updateRoad(Road road) throws DataNotFoundException {
		if (road.getId() == null || !roadRepo.findById(road.getId()).isPresent()) {
			throw new DataNotFoundException();
		}

		road = RoadFactory.createRoad(road);
		
		return roadRepo.save(road);
	}

	@Override
	public void deleteRoad(Long id) throws DataNotFoundException {
		if (id == null || !roadRepo.findById(id).isPresent()) {
			throw new DataNotFoundException();
		}

		roadRepo.deleteById(id);
	}

	@Override
	public List<Sign> getAllSigns() {
		return signRepo.findAll();
	}

	@Override
	public Sign getSign(Long id) throws DataNotFoundException {
		return signRepo.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public Sign addSign(Sign sign) throws DataAlreadyExistsException {
		if (sign.getId() != null && signRepo.findById(sign.getId()).isPresent()) {
			throw new DataAlreadyExistsException();
		}
		
		sign = SignFactory.createSign(sign);

		return signRepo.save(sign);
	}

	@Override
	public Sign updateSign(Sign sign) throws DataNotFoundException {
		if (sign.getId() == null || !signRepo.findById(sign.getId()).isPresent()) {
			throw new DataNotFoundException();
		}

		sign = SignFactory.createSign(sign);

		return signRepo.save(sign);
	}

	@Override
	public void deleteSign(Long id) throws DataNotFoundException {
		if (id == null || !signRepo.findById(id).isPresent()) {
			throw new DataNotFoundException();
		}

		signRepo.deleteById(id);
	}

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
		if (intersection.getId() == null || !interRepo.findById(intersection.getId()).isPresent()) {
			throw new DataNotFoundException();
		}

		intersection = IntersectionFactory.createIntersection(intersection);

		return interRepo.save(intersection);
	}

	@Override
	public void deleteIntersection(Long id) throws DataNotFoundException {
		if (id == null || !interRepo.findById(id).isPresent()) {
			throw new DataNotFoundException();
		}

		interRepo.deleteById(id);
	}
	
	@Override
	public void updateCreateIntersectionRoadBind(Intersection intersection, Set<Road> roads) throws DataNotFoundException {
		if (intersection.getId() == null || !interRepo.findById(intersection.getId()).isPresent()) {
			throw new DataNotFoundException();
		}

		for (Road road : roads) {
			if (road.getId() == null || !roadRepo.findById(road.getId()).isPresent()) {
				throw new DataNotFoundException();
			}
			
			Set<Road> intersectionRoads = intersection.getRoads();
			if (intersectionRoads == null) {
				intersectionRoads = new HashSet<Road>();
				intersection.setRoads(intersectionRoads);
			}
			intersectionRoads.add(road);
		}
		
		interRepo.save(intersection);
	}

	@Override
	public void deleteIntersectionRoadBind(Long intersectionId, Long roadId) throws DataNotFoundException {
		if (intersectionId == null || !interRepo.findById(intersectionId).isPresent()) {
			throw new DataNotFoundException();
		}

		if (roadId == null || !roadRepo.findById(roadId).isPresent()) {
			throw new DataNotFoundException();
		}

		Intersection intersection = interRepo.findById(intersectionId).get();
		intersection.getRoads().remove(roadRepo.findById(roadId).get());

		interRepo.save(intersection);
	}

	@Override
	public void updateCreateRoadSignBind(Road road, Sign sign) throws DataNotFoundException {
		if (road.getId() == null || !roadRepo.findById(road.getId()).isPresent()) {
			throw new DataNotFoundException();
		}

		if (sign.getId() == null || !signRepo.findById(sign.getId()).isPresent()) {
			throw new DataNotFoundException();
		}
		
		sign = signRepo.findById(sign.getId()).get();
		road = roadRepo.findById(road.getId()).get();
		
		sign.setRoad(road);
		signRepo.save(sign);
	}

	@Override
	public void deleteRoadSignBind(Long roadId, Long signId) throws DataNotFoundException {
		if (signId == null || !signRepo.findById(signId).isPresent()) {
			throw new DataNotFoundException();
		}

		if (roadId == null || !roadRepo.findById(roadId).isPresent()) {
			throw new DataNotFoundException();
		}

		Sign sign = signRepo.findById(signId).get();
		Road road = sign.getRoad();
		if (road != null && road.getId().equals(roadId)) {
			sign.setRoad(null);
			signRepo.save(sign);
		}
	}
}
