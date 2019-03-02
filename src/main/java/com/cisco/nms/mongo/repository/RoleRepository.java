package com.cisco.nms.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cisco.nms.model.Role;
import com.cisco.nms.model.RoleName;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(RoleName roleName);
}