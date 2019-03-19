package com.cisco.cdr.data;

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

import com.cisco.cdr.api.payload.CDRCountDTO;
import com.cisco.cdr.api.payload.Cdr;

@Repository
public interface CDRRepository extends CrudRepository<Cdr, Long>, JpaSpecificationExecutor<Cdr> {

	@Query("SELECT " + "    new com.cisco.cdr.api.payload.CDRCountDTO(cdr.status, COUNT(cdr)) " + "FROM "
			+ "    Cdr cdr WHERE cdr.dateAdded <= :startDate AND cdr.dateAdded >= :endDate" + " GROUP BY "
			+ "    cdr.status")
	public List<CDRCountDTO> fetchMessageCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public Page<Cdr> findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable);

	public Page<Cdr> findAll(Specification<Cdr> spec, Pageable pageable);
}
