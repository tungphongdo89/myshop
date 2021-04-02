package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query("select u from UserEntity u")
	Page<UserEntity> findUser(Pageable pageable);

	public UserEntity findByUsername(String username);
	
	public List<UserEntity> findByUsernameLike(String string);

}
