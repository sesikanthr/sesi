package com.cisco.nms;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cisco.nms.api.payload.Cdr;
import com.cisco.nms.data.CDRRepository;
import com.cisco.nms.service.impl.CDRServiceImpl;

public class TestMessageService {
	
	@Autowired
	private CDRRepository messageRepository;
	
	private CDRServiceImpl messageService;
	
	@Before
    public void setUp() throws Exception {
        
		messageService = new CDRServiceImpl();
		messageRepository  = EasyMock.createNiceMock(CDRRepository.class);
		messageService.setMessageRepository(messageRepository);
    }


	@Test
	public void testAdd() {
		
		Cdr message = new Cdr();
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
