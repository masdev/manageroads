package com.masintec.manageroads.service;

import java.util.List;
import java.util.Set;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Intersection;
import com.masintec.manageroads.model.Road;
import com.masintec.manageroads.model.Sign;

public interface ManageRoadsService {
	public List<Road> getAllRoads();
	public Road getRoad(Long id) throws DataNotFoundException ;
	public Road addRoad(Road road) throws DataAlreadyExistsException ;
	public Road updateRoad(Road road) throws DataNotFoundException ;
	public void deleteRoad(Long id) throws DataNotFoundException ;
	
	public List<Sign> getAllSigns();
	public Sign getSign(Long id) throws DataNotFoundException ;
	public Sign addSign(Sign sign) throws DataAlreadyExistsException ;
	public Sign updateSign(Sign sign) throws DataNotFoundException ;
	public void deleteSign(Long id) throws DataNotFoundException ;

	public List<Intersection> getAllIntersections();
	public Intersection getIntersection(Long id) throws DataNotFoundException ;
	public Intersection addIntersection(Intersection sign) throws DataAlreadyExistsException ;
	public Intersection updateIntersection(Intersection sign) throws DataNotFoundException ;
	public void deleteIntersection(Long id) throws DataNotFoundException;
	
	public void updateCreateIntersectionRoadBind(Intersection intersection, Set<Road> road) throws DataNotFoundException;
	public void deleteIntersectionRoadBind(Long intersectionId, Long roadId) throws DataNotFoundException;

	public void updateCreateRoadSignBind(Road road, Sign sign) throws DataNotFoundException;
	public void deleteRoadSignBind(Long roadId, Long signId) throws DataNotFoundException;
}
