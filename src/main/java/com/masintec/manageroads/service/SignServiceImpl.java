package com.masintec.manageroads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.factory.SignFactory;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.repository.SignRepository;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignRepository signRepo;

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
		if (sign.getId() == null || signRepo.findById(sign.getId()).isEmpty()) {
			throw new DataNotFoundException();
		}

		sign = SignFactory.createSign(sign);

		return signRepo.save(sign);
	}

	@Override
	public void deleteSign(Long signId) throws DataNotFoundException {
		if (signId == null || signRepo.findById(signId).isEmpty()) {
			throw new DataNotFoundException();
		}

		signRepo.deleteById(signId);
	}
}
