package com.tungphongdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tungphongdo.entity.SaleOffDetailEntity;

@Repository
public interface SaleOffDetailRepository extends JpaRepository<SaleOffDetailEntity, Integer>{

}
