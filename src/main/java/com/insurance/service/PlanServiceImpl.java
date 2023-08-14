package com.insurance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.entity.Plan;
import com.insurance.entity.PlanCategory;
import com.insurance.repository.PlanCategoryRepository;
import com.insurance.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanCategoryRepository planCategoryRepository;

	@Autowired
	private PlanRepository planRepository;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepository.findAll();

		Map<Integer, String> categorymap = new HashMap<>();
		categories.forEach(category -> {
			categorymap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categorymap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepository.save(plan);
		 return saved.getPlanCategoryID() != null;
	}

	@Override
	public List<Plan> getAllplans() {
		
		return planRepository.findAll();
	}

	@Override
	public Plan getPlanbyId(Integer planId) {
		Optional<Plan> findById = planRepository.findById(planId);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		planRepository.save(plan);// (upsert)
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletebyId(Integer planId) {
		boolean status = false;

		try {
			planRepository.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepository.findById(planId);

		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepository.save(plan);
			return true;
		}
		return false;
	}
}

	