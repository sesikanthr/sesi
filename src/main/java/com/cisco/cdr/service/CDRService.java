package com.cisco.cdr.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cisco.cdr.api.payload.AcctDataUsageDTO;
import com.cisco.cdr.api.payload.CDRCountDTO;
import com.cisco.cdr.api.payload.Cdr;

/**
 * 
 * @author dedadhic
 *
 */
public interface CDRService {
	
	public void add(Cdr message);

	public Optional<Cdr> findById(Long id);

	public Page<Cdr>  findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable);
	
	public List<CDRCountDTO>  getMessageCount(Date startDate, Date endDate);
	
	public List<AcctDataUsageDTO> fetchAcctDataUsage(Long operatorId, Date startDate, Date endDate);
	
	public Page<Cdr> findAllByFilter(Long id, Long operatorId, Long acctId, String status, Date dateAdded, Pageable pageable);

}
