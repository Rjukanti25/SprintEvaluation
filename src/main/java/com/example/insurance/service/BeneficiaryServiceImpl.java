package com.example.insurance.service;

import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Users;
import com.example.insurance.exception.ResourceNotFoundException;
import com.example.insurance.mapper.BeneficiaryMapper;
import com.example.insurance.repository.BeneficiaryRepository;
import com.example.insurance.repository.UsersRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapper;

    @Override
    public BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = beneficiaryMapper.convertFromDTO(beneficiaryDTO);
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.convertToDTO(savedBeneficiary);
    }

    @Override
    public boolean deleteBeneficiary(int beneficiaryId) {
    	System.out.println(beneficiaryId);
    	Beneficiary beneficiary= beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new ResourceNotFoundException("Beneficiary Id Not Found"));
    	System.out.println(beneficiary.getName());
    	Users user = beneficiary.getUser();
    	user.removeBeneficiary(beneficiary);
    	
    	Policy policy =beneficiary.getPolicies();
    	policy.removeBeneficiary(beneficiary);
    	
    	beneficiaryRepository.delete(beneficiary);
        return true;
    }

    @Override
    public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = beneficiaryMapper.convertFromDTO(beneficiaryDTO);
        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.convertToDTO(updatedBeneficiary);
    }

}
