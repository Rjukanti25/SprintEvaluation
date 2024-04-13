package com.example.insurance.service;

import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.mapper.BeneficiaryMapper;
import com.example.insurance.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        beneficiaryRepository.deleteById(beneficiaryId);
        return true;
    }

    @Override
    public BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = beneficiaryMapper.convertFromDTO(beneficiaryDTO);
        Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.convertToDTO(updatedBeneficiary);
    }


//    @Override
//    public List<BeneficiaryDTO> getByUserId(int userId) {
//        List<Beneficiary> beneficiaries = beneficiaryRepository.findByUserId(userId);
//        return beneficiaries.stream()
//                .map(beneficiaryMapper::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BeneficiaryDTO> getByPolicyId(int policyId) {
//        List<Beneficiary> beneficiaries = beneficiaryRepository.findByPolicyId(policyId);
//        return beneficiaries.stream()
//                .map(beneficiaryMapper::convertToDTO)
//                .collect(Collectors.toList());
//    }
}
