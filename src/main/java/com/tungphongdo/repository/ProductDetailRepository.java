package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.ProductDetailEntity;
import com.tungphongdo.entity.ProductEntity;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer>{
	
	@Query("select p from ProductDetailEntity p where p.productEntity.id = :id")
	public List<ProductDetailEntity> findByProductId(@Param("id") int id);
	
	@Query("select p.productEntity from ProductDetailEntity p order by p.date Desc")
	Page<ProductEntity> findLimitProductBySort(Pageable pageable);
	
	@Query("select p from ProductDetailEntity p")
	Page<ProductDetailEntity> findProductDetails(Pageable pageable);

	@Query("SELECT p from ProductDetailEntity p where p.productEntity.productName LIKE CONCAT('%',:productName,'%')")
	List<ProductDetailEntity> findProductsWithPartOfName(@Param("productName") String productName);

}
