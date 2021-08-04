package com.inkreta.receivables.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inkreta.receivables.response.ResponseMessage;
import com.inkreta.receivables.service.ReceivablesService;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ReceivablesController 
{
	@Autowired
	private ReceivablesService receivablesService;
	
	@GetMapping(value = { "/current/outstandings" })
	public ResponseEntity<ResponseMessage> findCurrentOutstandings() 
	{
		BigDecimal resultData = receivablesService.findCurrentOutstandings("findCurrentOutstandings");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData.longValue()));
	}
	
	@GetMapping(value = { "/current/analysis" })
	public ResponseEntity<ResponseMessage> findAnalysis() 
	{
		List<Map<String, Object>> resultData = receivablesService.findCurrentAnalysis("findCurrentAnalysis");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
	}
	
	@GetMapping(value = { "/current/overdue" })
	public ResponseEntity<ResponseMessage> findCurrentOverdue() 
	{
		BigDecimal resultData = receivablesService.findCurrentOverdue("findCurrentOverdue");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData.longValue()));
	}
	
	@GetMapping(value = { "/customer/analysis" })
	public ResponseEntity<ResponseMessage> findCustomerAnalysis() 
	{
		List<Map<String, Object>> resultData = receivablesService.findCustomerAnalysis("findCustomerAnalysis");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(resultData));
	}
}
