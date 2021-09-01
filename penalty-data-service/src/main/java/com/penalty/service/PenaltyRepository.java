package com.penalty.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.penalty.bean.Penalty;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long>{

	public Penalty findByVin(String vin);
}
