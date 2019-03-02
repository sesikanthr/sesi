package com.cisco.nms.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cisco.nms.api.payload.Message;
import com.cisco.nms.api.payload.MessageCountDTO;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>, JpaSpecificationExecutor<Message> {

	@Query("SELECT " + "    new com.cisco.nms.api.payload.MessageCountDTO(msg.type, COUNT(msg)) " + "FROM "
			+ "    Message msg WHERE msg.dateAdded <= :startDate AND msg.dateAdded >= :endDate" + " GROUP BY "
			+ "    msg.type")
	public List<MessageCountDTO> fetchMessageCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public Page<Message> findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable);

	public Page<Message> findAll(Specification<Message> spec, Pageable pageable);
}
