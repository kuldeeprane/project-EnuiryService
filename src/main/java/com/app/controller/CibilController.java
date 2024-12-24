package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CibilRequestDTO;
import com.app.dto.EnquiryResponseDTO;
import com.app.entity.Cibil;
import com.app.resource.EnquiryResource;
import com.app.service.CibilService;

@RestController
public class CibilController {
	@Autowired
	private EnquiryResource enquiryResource;

	@Autowired
	private CibilService cibilService;

	@PostMapping(value = "/cibils")
	public ResponseEntity<EnquiryResponseDTO> saveCibilDetails(@RequestBody CibilRequestDTO cibilRequestDTO) {
		enquiryResource.generatedRandomCibilScore(cibilRequestDTO);

		return new ResponseEntity<EnquiryResponseDTO>(HttpStatus.CREATED);
	}

}
