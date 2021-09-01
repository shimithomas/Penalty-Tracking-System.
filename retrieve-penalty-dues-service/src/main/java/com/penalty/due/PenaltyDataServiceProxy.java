package com.penalty.due;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="penalty-data-service")
public interface PenaltyDataServiceProxy {

	@GetMapping("/penalty/vin")
	public @ResponseBody DefaultersServiceResponse retrievePenalty(@RequestParam String[] vins) ;

}
