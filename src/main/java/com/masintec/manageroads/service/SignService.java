package com.masintec.manageroads.service;

import java.util.List;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Sign;

import jakarta.transaction.Transactional;

@Transactional
public interface SignService {
	public List<Sign> getAllSigns();

	public Sign getSign(Long id) throws DataNotFoundException;

	public Sign addSign(Sign sign) throws DataAlreadyExistsException;

	public Sign updateSign(Sign sign) throws DataNotFoundException;

	public void deleteSign(Long id) throws DataNotFoundException;
}
