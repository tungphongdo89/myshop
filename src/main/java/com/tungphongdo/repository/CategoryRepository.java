package com.tungphongdo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{
	
	@Query("select c from CategoryEntity c")
	Page<CategoryEntity> findCategory(Pageable pageable);
	
	public List<CategoryEntity> findByCategoryNameLike(String string);
}
