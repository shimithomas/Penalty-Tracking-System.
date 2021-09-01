package com.penalty.due;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.penalty.due.exception.RecordNotFoundException;

import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class RetrieveDefaultersController {

	
	@Autowired
	private PenaltyDataServiceProxy proxy;
	@GetMapping("/dues")
	//@Retry(name="get-dues", fallbackMethod = "fallbackRetrievePenalty")
	@Retry(name="default", fallbackMethod = "fallbackRetrievePenalty")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackRetrievePenalty")

	public ResponseEntity<DefaultersServiceResponse> retrievePenalty(@RequestParam String[] vins) {

		System.out.println("Inside retrievePenalty");
		DefaultersServiceResponse penServiceResponse  = null;
		try {
			penServiceResponse = proxy.retrievePenalty(vins);	
		}
		catch(Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
			throw new RecordNotFoundException(("VIN not found in database"));
		}
		

		return new ResponseEntity<DefaultersServiceResponse>(penServiceResponse, HttpStatus.OK);
	}
	
	private ResponseEntity<String> fallbackRetrievePenalty(RecordNotFoundException e){
	    return new ResponseEntity<>("VIN not found in database", HttpStatus.NOT_FOUND);
	  }
	

}
