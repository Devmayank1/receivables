package com.inkreta.receivables.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkreta.receivables.repository.ReceivablesRepository;
import com.inkreta.receivables.service.ReceivablesService;

@Service
public class ReceivablesServiceImpl implements ReceivablesService {

	@Autowired
	private ReceivablesRepository receivablesRepository;
	
	@Override
	public BigDecimal findCurrentOutstandings(String queryName) {
		return receivablesRepository.findCurrentData( queryName );
	}

	@Override
	public List<Map<String, Object>> findCurrentAnalysis(String queryName) {
		return receivablesRepository.findAnalysis( queryName );
	}

	@Override
	public BigDecimal findCurrentOverdue(String queryName) {
		return receivablesRepository.findCurrentData( queryName );
	}

	@Override
	public List<Map<String, Object>> findCustomerAnalysis(String queryName) {
		return receivablesRepository.findAnalysis( queryName );
	}
	
}
