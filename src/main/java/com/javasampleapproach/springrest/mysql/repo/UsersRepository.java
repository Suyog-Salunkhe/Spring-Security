package com.javasampleapproach.springrest.mysql.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.javasampleapproach.springrest.mysql.model.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {
	
	List<Users> findByUsername(String name);
	
	@Query("UPDATE Users u SET u.lastLogin=:lastLogin WHERE u.username = ?#{ principal?.username }")
    @Modifying
    @Transactional
    public void updateLastLogin(@Param("lastLogin") Date lastLogin);
	
}
