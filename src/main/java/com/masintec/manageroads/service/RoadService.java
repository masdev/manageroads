package com.masintec.manageroads.service;

import java.util.List;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Road;

import jakarta.transaction.Transactional;

@Transactional
public interface RoadService {
	public List<Road> getAllRoads();
	public Road getRoad(Long id) throws DataNotFoundException;

	public Road addRoad(Road road) throws DataAlreadyExistsException;

	public Road updateRoad(Road road) throws DataNotFoundException;

	public void deleteRoad(Long id) throws DataNotFoundException;

	public void updateCreateRoadSignBind(Long roadId, Long signId) throws DataNotFoundException;

	public void deleteRoadSignBind(Long roadId, Long signId) throws DataNotFoundException;
}
