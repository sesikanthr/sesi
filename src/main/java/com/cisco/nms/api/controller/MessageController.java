package com.cisco.nms.api.controller;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cisco.nms.api.payload.Message;
import com.cisco.nms.api.payload.MessageCountDTO;
import com.cisco.nms.service.MessageService;

/**
 * 
 * @author dedadhic
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/messages")
public class MessageController {

	private static Logger LOGGER = LogManager.getFormatterLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Message> createMessage(@RequestBody Message message, UriComponentsBuilder builder) {

		LOGGER.debug("Start of createMessage method. message={}", message);
		messageService.add(message);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/{id}").buildAndExpand(message.getId()).toUri());
		return new ResponseEntity<Message>(message, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {

		LOGGER.debug("Start of getMessage method. id={}", id);
		Optional<Message> optional = messageService.findById(id);
		return new ResponseEntity<Message>(optional.get(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Message>> searchMessages(@RequestParam("id") Optional<Long> id,
			@RequestParam("source") Optional<String> source, @RequestParam("type") Optional<String> type,
			@RequestParam("description") Optional<String> description,
			@RequestParam("dateAdded") Optional<Date> dateAdded, Pageable pageRequest) {

		LOGGER.debug("Start of searchMessages method. pageRequest={}", pageRequest);

		Page<Message> page = null;

		if (id.isPresent() || (source.isPresent() && !source.get().isEmpty())
				|| (type.isPresent() && !type.get().isEmpty())
				|| (description.isPresent() && !description.get().isEmpty()) || dateAdded.isPresent()) {
			page = messageService.findAllByFilter(id.orElse(null), source.orElse(null), type.orElse(null),
					description.orElse(null), dateAdded.orElse(null), pageRequest);
		} else {
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.HOUR_OF_DAY, -24);
			page = messageService.findByDateAddedBetween(c.getTime(), currentDate, pageRequest);
		}

		return new ResponseEntity<Page<Message>>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<List<MessageCountDTO>> getMessageCount() {

		LOGGER.debug("Start of getMessageCount method.");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.HOUR_OF_DAY, -24);
		return new ResponseEntity<List<MessageCountDTO>>(messageService.getMessageCount(currentDate, c.getTime()),
				HttpStatus.OK);
	}

}
