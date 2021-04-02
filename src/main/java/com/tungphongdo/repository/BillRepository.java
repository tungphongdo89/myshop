package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.BillEntity;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Integer>{
	
	@Query("select b from BillEntity b")
	Page<BillEntity> findBills(Pageable pageable);
	
	@Query("SELECT b from BillEntity b where b.date LIKE CONCAT('%',:date,'%')")
	List<BillEntity> findBillsWithDate(@Param("date") String date);

}
