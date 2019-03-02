package com.cisco.nms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cisco.nms.api.payload.Message;
import com.cisco.nms.api.payload.MessageCountDTO;

/**
 * 
 * @author dedadhic
 *
 */
public interface MessageService {
	
	public void add(Message message);

	public Optional<Message> findById(Long id);

	public Page<Message>  findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable);
	
	public List<MessageCountDTO>  getMessageCount(Date startDate, Date endDate);
	
	public Page<Message> findAllByFilter(Long id, String source, String type, String description, Date dateAdded, Pageable pageable);

}
