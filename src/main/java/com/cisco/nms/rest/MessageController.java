package com.cisco.nms.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cisco.nms.api.model.Message;
import com.cisco.nms.api.model.MessageCountDTO;
import com.cisco.nms.service.MessageService;

/**
 * 
 * @author dedadhic
 *
 */
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
	public ResponseEntity<Page<Message>> searchMessages(Pageable pageRequest) {

		LOGGER.debug("Start of searchMessages method. pageRequest={}", pageRequest);
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.HOUR_OF_DAY, -24);
		Page<Message> page = new PageImpl<Message>(messageService.findByDateAddedBetween(c.getTime(), currentDate, pageRequest));
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
