package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.ProductDetailBillEntity;
import com.tungphongdo.entity.ProductDetailEntity;

@Repository
public interface ProductDetailBillRepository extends JpaRepository<ProductDetailBillEntity, Integer>{
	
	@Query("select p.productDetailEntity from ProductDetailBillEntity p order by p.boughtAmount Desc")
	Page<ProductDetailEntity> findLimitProductDetailBySort(Pageable pageable);
	
	@Query("select p from ProductDetailBillEntity p where p.productDetailEntity.id = ?1")
	public List<ProductDetailBillEntity> findByProductDetailId(int id);
	
	@Query("select p from ProductDetailBillEntity p where p.billEntity.id = ?1")
	public List<ProductDetailBillEntity> findByBillId(int id);

}
