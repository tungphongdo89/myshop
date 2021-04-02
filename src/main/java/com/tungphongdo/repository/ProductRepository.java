package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	@Query("select p from ProductEntity p")
	Page<ProductEntity> findProducts(Pageable pageable);
	
	public List<ProductEntity> findByProductNameLike(String string);
	
	@Query("select p from ProductEntity p where p.categoryEntity.id = ?1")
	public List<ProductEntity> findByCategoryId(int id);

}
