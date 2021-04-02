package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.SizeEntity;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer>{
	
	@Query("select s from SizeEntity s")
	Page<SizeEntity> findSize(Pageable pageable);
	
	public List<SizeEntity> findBySizeNameLike(String string);

}
