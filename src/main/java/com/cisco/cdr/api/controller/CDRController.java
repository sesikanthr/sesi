package com.cisco.cdr.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cisco.cdr.api.payload.CDRCountDTO;
import com.cisco.cdr.api.payload.Cdr;
import com.cisco.cdr.service.CDRService;

/**
 * 
 * @author dedadhic
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/cdrs")
public class CDRController {

	private static Logger LOGGER = LogManager.getFormatterLogger(CDRController.class);

	@Autowired
	private CDRService cdrService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cdr> createMessage(@RequestBody Cdr message, UriComponentsBuilder builder) {

		LOGGER.debug("Start of createMessage method. message={}", message);
		cdrService.add(message);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/{id}").buildAndExpand(message.getCdrId()).toUri());
		return new ResponseEntity<Cdr>(message, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cdr> getMessage(@PathVariable("id") Long id) {

		LOGGER.debug("Start of getMessage method. id={}", id);
		Optional<Cdr> optional = cdrService.findById(id);
		return new ResponseEntity<Cdr>(optional.get(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Cdr>> searchMessages(@RequestParam("cdrId") Optional<Long> cdrId,
			@RequestParam("operatorId") Optional<Long> operatorId, @RequestParam("acctId") Optional<Long> acctId,
			@RequestParam("status") Optional<String> status, @RequestParam("dateAdded") Optional<Date> dateAdded,
			Pageable pageRequest) {

		LOGGER.debug("Start of searchMessages method. pageRequest={}", pageRequest);

		Page<Cdr> page = null;

		if (cdrId.isPresent() || operatorId.isPresent() || acctId.isPresent()
				|| (status.isPresent() && !status.get().isEmpty()) || dateAdded.isPresent()) {
			page = cdrService.findAllByFilter(cdrId.orElse(null), operatorId.orElse(null), acctId.orElse(null),
					status.orElse(null), dateAdded.orElse(null), pageRequest);
		} else {
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.HOUR_OF_DAY, -24);
			page = cdrService.findByDateAddedBetween(c.getTime(), currentDate, pageRequest);
		}

		return new ResponseEntity<Page<Cdr>>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<List<CDRCountDTO>> getMessageCount() {

		LOGGER.debug("Start of getMessageCount method.");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.HOUR_OF_DAY, -24);
		return new ResponseEntity<List<CDRCountDTO>>(cdrService.getMessageCount(currentDate, c.getTime()),
				HttpStatus.OK);
	}

}
