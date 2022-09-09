package com.skillstorm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.id.AuthorityId;
import com.skillstorm.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, AuthorityId>{

}
