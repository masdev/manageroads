package com.masintec.manageroads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masintec.manageroads.model.Intersection;

@Repository
public interface IntersectionRepository extends JpaRepository<Intersection, Long> {

}
