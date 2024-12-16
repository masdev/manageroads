package com.masintec.manageroads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masintec.manageroads.model.Sign;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {

}
