package com.cisco.nms;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cisco.nms.api.payload.Message;
import com.cisco.nms.data.MessageRepository;
import com.cisco.nms.service.impl.MessageServiceImpl;

public class TestMessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	private MessageServiceImpl messageService;
	
	@Before
    public void setUp() throws Exception {
        
		messageService = new MessageServiceImpl();
		messageRepository  = EasyMock.createNiceMock(MessageRepository.class);
		messageService.setMessageRepository(messageRepository);
    }


	@Test
	public void testAdd() {
		
		Message message = new Message();
		EasyMock.expect(messageRepository.save(message)).andReturn(message);
		EasyMock.replay(messageRepository);
		messageService.add(message);
		
	}

	@Test
	public void testFindById() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindByDateAddedBetween() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetMessageCount() {
		//fail("Not yet implemented");
	}

}
