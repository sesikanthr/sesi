package com.cisco.nms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.cisco.nms.api.model.Message;
import com.cisco.nms.api.model.MessageCountDTO;

/**
 * 
 * @author dedadhic
 *
 */
public interface MessageService {
	
	public void add(Message message);

	public Optional<Message> findById(Long id);

	public List<Message>  findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable);
	
	public List<MessageCountDTO>  getMessageCount(Date startDate, Date endDate);

}
