package com.example.insurance.service;

import java.util.List;

import com.example.insurance.dto.PolicyDTO;

public interface PolicyService {

	PolicyDTO savePolicy(int userId, PolicyDTO policyDTO);
	 
	boolean deletePolicy(int id);
 
	PolicyDTO updatePolicy(PolicyDTO policyDTO);
 
	PolicyDTO getByPolicyId(Integer id);
 
	List<PolicyDTO> getByUserId(int userId);
	
	List<PolicyDTO> viewAllPolicyDetails();
 
	List<PolicyDTO> getByPremium(int premium);
}
