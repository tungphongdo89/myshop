package com.tungphongdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.SaleOffEntity;

@Repository
public interface SaleOffRepository extends JpaRepository<SaleOffEntity, Integer>{

}
