package com.training.gcp.cicdgcp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CurrencyConverter {
	
	@GetMapping("/rstousd/{quantity}")
	public ResponseEntity<String> convertRsToUsd(@PathVariable double quantity) {
		double amount=quantity/80.5;
		return new ResponseEntity("$ " + amount,HttpStatus.OK);
		
	}
	@GetMapping("/usdtors/{quantity}")
	public ResponseEntity<String> convertUsdToRs(@PathVariable double quantity) {
		double amount=quantity*80.5;
		return new ResponseEntity("Rs " + amount,HttpStatus.OK);
		
	}

}
