package com.example.gcp.cicdgcptraining.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CurrencyConverter {
	
	@GetMapping("/rstousd/{quantity}")
	public ResponseEntity<Double> convertRupeestodollar(@PathVariable double quantity) {
		double amount=quantity/80.5;
		return new ResponseEntity(amount,HttpStatus.OK);
		
	}
	@GetMapping("/usdtors/{quantity}")
	public ResponseEntity<Double> convertdollartors(@PathVariable double quantity) {
		double amount=quantity*80.5;
		return new ResponseEntity(amount,HttpStatus.OK);
		
	}

}
