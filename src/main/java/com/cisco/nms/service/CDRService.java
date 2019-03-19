package com.cisco.nms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cisco.nms.api.payload.Cdr;
import com.cisco.nms.api.payload.CDRCountDTO;

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
	
	public Page<Cdr> findAllByFilter(Long id, Long operatorId, Long acctId, String status, Date dateAdded, Pageable pageable);

}
