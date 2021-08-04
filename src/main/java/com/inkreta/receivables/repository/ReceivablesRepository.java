package com.inkreta.receivables.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReceivablesRepository {
	@Autowired
	private QueryRepository queryRepository;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public BigDecimal findCurrentData(String queryName) {
		final String kpiSql = queryRepository.findQuery(queryName);
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedJdbcTemplate.queryForObject(kpiSql, params, BigDecimal.class);
	}

	public List<Map<String, Object>> findAnalysis(String queryName) {
	 final String kpiSql = queryRepository.findQuery(queryName);
		MapSqlParameterSource params = new MapSqlParameterSource();
		return namedJdbcTemplate.queryForList(kpiSql, params);
	}

}
