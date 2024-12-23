package com.masintec.manageroads.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.factory.SignFactory;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.model.Sign.RoadSide;
import com.masintec.manageroads.model.Sign.SignType;
import com.masintec.manageroads.repository.SignRepository;
import com.masintec.manageroads.service.SignServiceImpl;

@ExtendWith (MockitoExtension.class)
public class SignServiceTest {
	
	@InjectMocks
	private SignServiceImpl sService;

	@Mock
	private SignRepository signRepo;
	
	//Sign(SignType signType, String name, String description, String side, Long point) {
	
	@Test
	public void findAllSignsTest() {
		List<Sign> signs = new ArrayList<Sign>();
		Sign a1 = SignFactory.createSign(SignType.GUIDE, "To the left", "Turn to the left", RoadSide.LEFT, 1234L, null);
		Sign a2 = SignFactory.createSign(SignType.WARNING, "Steep road", "Steep road in 100m", RoadSide.LEFT, 1200L, null);
		Sign a3 = SignFactory.createSign(SignType.INFORMATION, "Hospital", "Hospital", RoadSide.LEFT, 1104L, null);

		signs.add(a1);
		signs.add(a2);
		signs.add(a3);
		
		when(signRepo.findAll()).thenReturn(signs);
		
		List<Sign> allSigns = sService.getAllSigns();
		
		assertEquals(allSigns.size(), 3);
		verify(signRepo, times(1)).findAll();
	}
	
	@Test
	public void createOrSaveSignTest() throws DataAlreadyExistsException {
		Sign a1 = SignFactory.createSign(SignType.GUIDE, "To the left", "Turn to the left", RoadSide.LEFT, 1234L, null);

		a1 = sService.addSign(a1);

		assertNotNull(a1);
		verify(signRepo, times(1)).save(a1);
	}
}
