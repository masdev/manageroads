package com.masintec.manageroads.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.factory.RoadFactory;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.repository.RoadRepository;
import com.masintec.manageroads.repository.SignRepository;

@Service
public class RoadServiceImpl implements RoadService {

	@Autowired
	private RoadRepository roadRepo;

	@Autowired
	private SignRepository signRepo;


	public RoadServiceImpl() {
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
			Optional<Road> roadOpt = roadRepo.findById(road.getId());
			if (roadOpt.isPresent()) {
				throw new DataAlreadyExistsException();
			}
		}

		road = RoadFactory.createRoad(road);

		return roadRepo.save(road);
	}
	
	@Override
	public Road updateRoad(Road road) throws DataNotFoundException {
		if (road.getId() == null) {
			throw new IllegalArgumentException();
		}

		if (roadRepo.findById(road.getId()).isEmpty()) {
			throw new DataNotFoundException();
		}

		road = RoadFactory.createRoad(road);
		
		return roadRepo.save(road);
	}

	@Override
	public void deleteRoad(Long roadId) throws DataNotFoundException {
		if (roadId == null) {
			throw new IllegalArgumentException();
		}

		if (roadRepo.findById(roadId).isEmpty()) {
			throw new DataNotFoundException();
		}

		roadRepo.deleteById(roadId);
	}

	@Override
	public void updateCreateRoadSignBind(Long roadId, Long signId) throws DataNotFoundException {
		if (roadId == null || signId == null) {
			throw new IllegalArgumentException();
		}

		Optional <Sign> signOpt = signRepo.findById(signId);
		if (signOpt.isEmpty()) {
			throw new DataNotFoundException();
		}
		Sign sign = signOpt.get();

		Optional <Road> roadOpt = roadRepo.findById(roadId);
		if (roadOpt.isEmpty()) {
			throw new DataNotFoundException();
		}
		Road road = roadOpt.get();

		sign.setRoad(road);
		signRepo.save(sign);
	}

	@Override
	public void deleteRoadSignBind(Long roadId, Long signId) throws DataNotFoundException {
		if (signId == null || roadId == null) {
			throw new IllegalArgumentException();
		}

		Optional <Sign> signOpt = signRepo.findById(signId);
		if (signOpt.isEmpty()) {
			throw new DataNotFoundException();
		}
		Sign sign = signOpt.get();

		Road road = sign.getRoad();
		if (road != null && road.getId().equals(roadId)) {
			sign.setRoad(null);
			signRepo.save(sign);
		}
	}
}
