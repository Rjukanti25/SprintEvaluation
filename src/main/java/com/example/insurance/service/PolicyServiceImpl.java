package com.example.insurance.service;

import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Users;
import com.example.insurance.mapper.PolicyMapper;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UsersRepository;
 
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

	PolicyRepository policyRepo;
	UsersRepository userRepo;
	PolicyMapper policyMapper;
 
	@Override
	public PolicyDTO savePolicy(int userId, PolicyDTO policyDTO) {
		Users user = userRepo.findById(userId).get();
		Policy policy = policyMapper.convertFromDTO(policyDTO);
//		user.addPolicy(policy);
		policy.addUsers(user);
		return policyMapper.convertToDTO(policyRepo.save(policy));
	}
 
	@Override
	public boolean deletePolicy(int id) {
		// Find the policy entity by policy ID
		Policy policy = policyRepo.findById(id).orElseThrow(() -> new RuntimeException());
 
		// If policy entity is found, delete it
		policyRepo.delete(policy);
 
		// If policy entity is not found, return false
		return false;
	}
 
	@Override
	public PolicyDTO updatePolicy(PolicyDTO policyDTO) {
 
		// Save the updated user
		Policy updatedPolicy = policyRepo.save(policyMapper.convertFromDTO(policyDTO));
 
		// Map updated User entity back to UserDTO
		return policyMapper.convertToDTO(updatedPolicy);
	}
	
	@Override
	public List<PolicyDTO> viewAllPolicyDetails()
	{
		//Get details of all policies 
		List<Policy> policies=policyRepo.findAll();
		// Map the list of Policy entities to a list of PolicyDTO
		return  policies.stream()
				.map(policyMapper::convertToDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public PolicyDTO getByPolicyId(Integer id) {
		// Retrieve the policy from the repository by its ID
		Policy policy = policyRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Policy not found with ID: " + id));
 
		// Map the Policy entity to a PolicyDTO
		PolicyDTO policyDTO = policyMapper.convertToDTO(policy); // Assuming policyMapper is your mapper for Policy to
																	// PolicyDTO
 
		return policyDTO;
	}
 
	@Override
	public List<PolicyDTO> getByUserId(int userId) {
		// Retrieve policies associated with the user ID from the repository
		Users user = userRepo.findById(userId).get();
		List<Policy> policies = user.getPolicies();
		// Map the list of Policy entities to a list of PolicyDTO
		List<PolicyDTO> policyDTOs = policies.stream().map(p -> policyMapper.convertToDTO(p)) // Assuming policyMapper
																								// is your mapper for
																								// Policy to PolicyDTO
				.toList();
 
		return policyDTOs;
	}
 
	@Override
	public List<PolicyDTO> getByPremium(int premium) {
	    // Retrieve the policies from the repository by their premium
	    List<Policy> policies = policyRepo.findByPremium(premium);
//	            .orElseThrow(() -> new IllegalArgumentException("Policies not found with premium: " + premium));
 
	    // Map the list of Policy entities to a list of PolicyDTO
	    List<PolicyDTO> policyDTOs = policies.stream()
	            .map(policyMapper::convertToDTO) // Assuming policyMapper is your mapper for Policy to PolicyDTO
	            .collect(Collectors.toList());
 
	    return policyDTOs;
	}

}
