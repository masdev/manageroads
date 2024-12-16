package com.masintec.manageroads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masintec.manageroads.model.Road;

@Repository
public interface RoadRepository extends JpaRepository<Road, Long> {

}
