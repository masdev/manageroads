package com.masintec.manageroads.service;

import java.util.List;
import java.util.Set;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Intersection;

import jakarta.transaction.Transactional;

@Transactional
public interface IntersectionService {
	public List<Intersection> getAllIntersections();

	public Intersection getIntersection(Long id) throws DataNotFoundException;

	public Intersection addIntersection(Intersection sign) throws DataAlreadyExistsException;

	public Intersection updateIntersection(Intersection sign) throws DataNotFoundException;

	public void deleteIntersection(Long id) throws DataNotFoundException;
	
	public void updateCreateIntersectionRoadBind(Long intersectionId, Set<Long> roadIds) throws DataNotFoundException;

	public void deleteIntersectionRoadBind(Long intersectionId, Long roadId) throws DataNotFoundException;
}
