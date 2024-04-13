package com.example.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer>{

//	List<Beneficiary> findByUserId(int userId);
//
//	List<Beneficiary> findByPolicyId(int policyId);

}
