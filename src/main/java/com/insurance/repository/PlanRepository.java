package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
