package com.TOgun1.csv_parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TOgun1.csv_parser.model.Shopper;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
}
