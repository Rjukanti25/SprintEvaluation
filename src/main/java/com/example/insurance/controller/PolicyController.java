package com.example.insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.service.PolicyService;
import com.example.insurance.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/insurance/policies")
public class PolicyController {

	PolicyService policyService;

    @PostMapping("/save/{userId}")
    public ResponseEntity<PolicyDTO> savePolicy(@PathVariable("userId") int userId,@RequestBody PolicyDTO policyDTO) {
        PolicyDTO savedPolicy = policyService.savePolicy(userId,policyDTO);
        return new ResponseEntity<PolicyDTO>(savedPolicy,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePolicy(@PathVariable("id") int policyId) {
        boolean deleted = policyService.deletePolicy(policyId);
        return new ResponseEntity<Boolean>(deleted,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PolicyDTO> updatePolicy(@RequestBody PolicyDTO policyDTO) {
        PolicyDTO updatedPolicy = policyService.updatePolicy(policyDTO);
        return new ResponseEntity<PolicyDTO>(updatedPolicy,HttpStatus.OK);
   
    }

    @GetMapping("/all")
    public ResponseEntity<List<PolicyDTO>> viewAllPolicyDetails() {
        List<PolicyDTO> policies = policyService.viewAllPolicyDetails();
        return new ResponseEntity<List<PolicyDTO>>(policies,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTO> getByPolicyId(@PathVariable Integer id) {
        PolicyDTO policy = policyService.getByPolicyId(id);
        return ResponseEntity.ok(policy);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PolicyDTO>> getByUserId(@PathVariable int userId) {
        List<PolicyDTO> policies = policyService.getByUserId(userId);
        return ResponseEntity.ok(policies);
    }
    @GetMapping("/premium/{premium}")
    public ResponseEntity<List<PolicyDTO>> getPoliciesByPremium(@PathVariable("premium") int premium) {
        List<PolicyDTO> policies = policyService.getByPremium(premium);
        return ResponseEntity.ok(policies);
    }

}
