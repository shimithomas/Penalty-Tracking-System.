package com.penalty.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.penalty.bean.Penalty;
import com.penalty.bean.PenaltyServiceResponse;
import com.penalty.exception.RecordNotFoundException;
import com.penalty.service.PenaltyRepository;
import com.penalty.service.RecordPenaltyService;

@RestController
public class PenaltyDataLoadController {


	@Autowired
	private PenaltyRepository repository;	
	@Autowired
	private RecordPenaltyService recordPenaltyService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/penalty/owners")
	public ResponseEntity<String> recordPenalty() {

		ArrayList<Penalty> penaltyList  = recordPenaltyService.readPenaltyDetails();		
		
		if (penaltyList !=null) {
			repository.saveAll(penaltyList);
			logger.info("Records successfully saved into Database");
			return new ResponseEntity<String>("Successfully Inserted", HttpStatus.OK);
		}
		logger.info("No records to process in file");	
		return new ResponseEntity<String>("No records to process", HttpStatus.OK);
	}


	@GetMapping("/penalty/vin")
	public ResponseEntity<PenaltyServiceResponse> retrievePenalty(@RequestParam String[] vins) {
					
		PenaltyServiceResponse penServiceResponse = new PenaltyServiceResponse();
		ArrayList<Penalty> penaltyList = new ArrayList<Penalty>();
		for(String vin: vins) {
			Penalty pen = repository.findByVin(vin);
			if (pen !=null)
				penaltyList.add(pen);
		}
		if(penaltyList.isEmpty())
			 throw new RecordNotFoundException(("VIN not found in database"));
		
		penServiceResponse.setPenaltyList(penaltyList);
		logger.info("Successfully retrieved details from database");
		return new ResponseEntity<PenaltyServiceResponse>(penServiceResponse, HttpStatus.OK);
		
	}
}
