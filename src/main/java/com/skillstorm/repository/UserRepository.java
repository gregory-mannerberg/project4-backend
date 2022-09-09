package com.skillstorm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
