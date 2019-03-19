package com.cisco.cdr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cisco.cdr.api.payload.CDRCountDTO;
import com.cisco.cdr.api.payload.Cdr;
import com.cisco.cdr.data.CDRRepository;
import com.cisco.cdr.data.CDRSpecification;
import com.cisco.cdr.service.CDRService;

/**
 * 
 * Service Layer to Add, Search and Fetch messages count.
 * 
 * @author dedadhic
 *
 */
@Service
public class CDRServiceImpl implements CDRService {
	
	private static Logger LOGGER = LogManager.getFormatterLogger(CDRServiceImpl.class);
	
	@Autowired
	private CDRRepository cdrRepository;

	@Override
	public void add(Cdr message) {
		
		LOGGER.debug("Start of add method. message={}",message);
		cdrRepository.save(message);
	}

	@Override
	public Optional<Cdr> findById(Long id) {
		
		LOGGER.debug("Start of findById method. id={}",id);
		return cdrRepository.findById(id);
	}

	@Override
	public Page<Cdr> findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable) {
		
		LOGGER.debug("Start of findByDateAddedBetween method. startDate={}, endDate={}",startDate, endDate);
		return cdrRepository.findByDateAddedBetween(startDate, endDate, pageable);
	}

	@Override
	public List<CDRCountDTO> getMessageCount(Date startDate, Date endDate) {
		
		LOGGER.debug("Start of getMessageCount method. startDate={}, endDate={}",startDate, endDate);
		return cdrRepository.fetchMessageCount(startDate, endDate);
	}
	
	@Override
	public Page<Cdr> findAllByFilter(Long id, Long operatorId, Long acctId, String status, Date dateAdded,
			Pageable pageable) {

		LOGGER.debug("Start of findAllByFilter method.");

		Cdr messageFilter = new Cdr();
		messageFilter.setCdrId(id);
		messageFilter.setOperatorId(operatorId);;
		messageFilter.setAcctId(acctId);;
		messageFilter.setStatus(status);
		messageFilter.setDateAdded(dateAdded);
		return cdrRepository.findAll(CDRSpecification.contain(messageFilter), pageable);
	}

	public void setCdrRepository(CDRRepository cdrRepository) {
		this.cdrRepository = cdrRepository;
	}

}
