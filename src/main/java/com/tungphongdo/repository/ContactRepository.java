package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{
	
	@Query("select c from ContactEntity c")
	Page<ContactEntity> findContacts(Pageable pageable);
	
	@Query("SELECT c from ContactEntity c where c.name LIKE CONCAT('%',:name,'%')")
	List<ContactEntity> findContactsWithPartOfName(@Param("name") String name);
}
