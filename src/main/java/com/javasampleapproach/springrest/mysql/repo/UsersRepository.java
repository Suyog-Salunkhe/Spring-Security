package com.javasampleapproach.springrest.mysql.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.springrest.mysql.model.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {
	
	List<Users> findByUsername(String name);
	
}
