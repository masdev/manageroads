package com.masintec.manageroads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masintec.manageroads.exception.DataAlreadyExistsException;
import com.masintec.manageroads.exception.DataNotFoundException;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.service.SignService;

@RestController
public class SignController {

	@Autowired
	private SignService sService;

	@GetMapping(path = "/signs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Sign> getAllSigns() {
		// System.out.println("getAllSigns");
		return sService.getAllSigns();
	}

	@GetMapping(path = "/signs/{id}")
	public Sign getSignById(@PathVariable Long id) throws DataNotFoundException {
		return sService.getSign(id);
	}

	/*
	 * { "signType": "WARNING", "id": 1500, "name": "Caution: falling stones",
	 * "description": "Simple sign", "side": "RIGHT", "point": 2245 }
	 */

	@PutMapping(path = "/signs", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Sign addSign(@RequestBody Sign sign) throws DataAlreadyExistsException {
		return sService.addSign(sign);
	}

	/*
	 * { "signType": "REGULATORY", "id": 3000, "road": { "roadType": "GRAVEL", "id":
	 * 1000, "name": "Gravel road M50", "description": "Simple road" }, "name":
	 * "No Parking", "description": "Simple sign", "side": "RIGHT", "point": 5641 }
	 */
	@PostMapping(path = "/signs", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Sign updateSign(@RequestBody Sign sign) throws DataNotFoundException {
		return sService.updateSign(sign);
	}

	@DeleteMapping(path = "/signs/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteSign(@PathVariable Long id) throws DataNotFoundException {
		sService.deleteSign(id);
	}
	
//	@PostMapping (path = "/roadsign", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//	public void bindRoadSign(@RequestBody ObjectNode objectNode) throws DataNotFoundException {
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode signJson = objectNode.findPath("sign");
//		JsonNode roadJson = objectNode.findPath("road");
//
//		Sign sign = mapper.convertValue(signJson, Sign.class);
//		Road road = mapper.convertValue(roadJson, Road.class);
//
//		mrService.updateCreateRoadSignBind(road, sign);
//	}
}
