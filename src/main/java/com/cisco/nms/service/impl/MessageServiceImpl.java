package com.cisco.nms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cisco.nms.api.payload.Message;
import com.cisco.nms.api.payload.MessageCountDTO;
import com.cisco.nms.data.MessageRepository;
import com.cisco.nms.data.MessageSpecification;
import com.cisco.nms.service.MessageService;

/**
 * 
 * Service Layer to Add, Search and Fetch messages count.
 * 
 * @author dedadhic
 *
 */
@Service
public class MessageServiceImpl implements MessageService {
	
	private static Logger LOGGER = LogManager.getFormatterLogger(MessageServiceImpl.class);
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void add(Message message) {
		
		LOGGER.debug("Start of add method. message={}",message);
		messageRepository.save(message);
	}

	@Override
	public Optional<Message> findById(Long id) {
		
		LOGGER.debug("Start of findById method. id={}",id);
		return messageRepository.findById(id);
	}

	@Override
	public Page<Message> findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable) {
		
		LOGGER.debug("Start of findByDateAddedBetween method. startDate={}, endDate={}",startDate, endDate);
		return messageRepository.findByDateAddedBetween(startDate, endDate, pageable);
	}

	@Override
	public List<MessageCountDTO> getMessageCount(Date startDate, Date endDate) {
		
		LOGGER.debug("Start of getMessageCount method. startDate={}, endDate={}",startDate, endDate);
		return messageRepository.fetchMessageCount(startDate, endDate);
	}
	
	@Override
	public Page<Message> findAllByFilter(Long id, String source, String type, String description, Date dateAdded,
			Pageable pageable) {

		LOGGER.debug("Start of findAllByFilter method.");

		Message messageFilter = new Message();
		messageFilter.setId(id);
		messageFilter.setSource(source);
		messageFilter.setType(type);
		messageFilter.setDescription(description);
		messageFilter.setDateAdded(dateAdded);
		return messageRepository.findAll(MessageSpecification.contain(messageFilter), pageable);
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

}
