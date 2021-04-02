package com.tungphongdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.AboutEntity;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Integer>{

	
}
