package com.inkreta.receivables.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ReceivablesService {

	BigDecimal findCurrentOutstandings(String queryName);

	List<Map<String, Object>> findCurrentAnalysis(String queryName);

	BigDecimal findCurrentOverdue(String queryName);

	List<Map<String, Object>> findCustomerAnalysis(String queryName);
	
}
