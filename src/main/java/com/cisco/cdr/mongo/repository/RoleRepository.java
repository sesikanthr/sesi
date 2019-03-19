package com.cisco.cdr.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cisco.cdr.model.Role;
import com.cisco.cdr.model.RoleName;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(RoleName roleName);
}