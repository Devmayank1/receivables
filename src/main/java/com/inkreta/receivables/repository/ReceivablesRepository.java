package com.inkreta.receivables.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReceivablesRepository {
	@Autowired
	private QueryRepository queryRepository;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

}
