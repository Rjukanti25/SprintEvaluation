package com.example.insurance.service;

import com.example.insurance.dto.BeneficiaryDTO;

import java.util.List;

public interface BeneficiaryService {

    BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO);

    boolean deleteBeneficiary(int beneficiaryId);

    BeneficiaryDTO updateBeneficiary(BeneficiaryDTO beneficiaryDTO);

//    List<BeneficiaryDTO> getByUserId(int userId);
//
//    List<BeneficiaryDTO> getByPolicyId(int policyId);
}
