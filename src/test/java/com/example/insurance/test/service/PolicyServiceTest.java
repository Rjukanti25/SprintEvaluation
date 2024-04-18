package com.example.insurance.test.service;
 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Transaction;
import com.example.insurance.entity.Users;
import com.example.insurance.mapper.PolicyMapper;
import com.example.insurance.mapper.UserMapper;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UsersRepository;
import com.example.insurance.service.PolicyServiceImpl;

 

//You can apply the extension by adding @ExtendWith(MockitoExtension.class) to the test class and annotating mocked fields with @Mock
@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {
//	@Mock allows us to create and inject a mock of PolicyRepository
	@Mock
    private PolicyRepository policyRepo;
//	@InjectMocks is used to create an instance of our service PolicyServiceImpl so that we can test it
    @InjectMocks
    private PolicyServiceImpl policyService;
    @Mock
    UsersRepository userRepo;
    @Mock
    PolicyMapper policyMapper;
    @Mock
    UserMapper userMapper;
    private Policy buildTestingPolicy() {
    	Policy policies = new Policy();
    	policies.setPolicyNumber(10);
        policies.setCoverageAmount(500000);
        policies.setDuration(36);
        policies.setStartDate(LocalDate.now());
        policies.setEndDate(LocalDate.of(2036, 04, 15));
        policies.setPremium(2000);
        return policies;
    }

 
	
    @Test
    void findAll_should_return_policy_test() {
        // Given
        Policy policy= this.buildTestingPolicy();
        // When
        when(policyRepo.findAll()).thenReturn(List.of(policy));
        List<PolicyDTO> policies = this.policyService.viewAllPolicyDetails();
        // Then
        Assertions.assertEquals(1, policies.size());
        verify(this.policyRepo).findAll();
    }
 
    
    public Policy getDummyPolicy() {
		Policy policy =new Policy();
		policy.setPolicyId(1);
		policy.setPolicyNumber(1001);
		policy.setCoverageAmount(1000000);
		policy.setDuration(36);
		policy.setStartDate(LocalDate.now());
		policy.setEndDate(LocalDate.of(2036,9, 22));
		policy.setPremium(499);
 
		return policy;
	}
    public Users getDummyUser() {
		Users user=new Users();
		user.setFirstName("Debyansh");
		user.setLastName("Shaw");
		user.setDateOfBirth(LocalDate.now());
		user.setAddress("Hawda");
		user.setCity("Kolkata");
		user.setState("kolkata");
		user.setZipCode("500039");
		user.setEmail("debyashshaw@gmail.com");
		user.setUsername("debyansh");
		user.setPassword("1234567890");
		return user;
	}
    @Test
    void getPolicyById_should_return_policy() {
        // Given
        Policy policy = getDummyPolicy();
        PolicyMapper m = new PolicyMapper();
        PolicyDTO d = m.convertToDTO(policy);
        // Mock repository behavior
        when(policyRepo.findById(1)).thenReturn(Optional.of(policy));
        when(policyMapper.convertToDTO(policy)).thenReturn(d);
        // When
        PolicyDTO returnedPolicy= policyService.getByPolicyId(1);
        // Then
        Assertions.assertEquals(policy.getPolicyId(), returnedPolicy.getPolicyId());
        verify(policyRepo).findById(1);
    }
    @Test
	  void addPolicy_should_return_added_policy() {
	      // Given
	      Policy policy= getDummyPolicy();
	      Users user = getDummyUser();
	      PolicyDTO policyDTO = new PolicyDTO();
	      policyDTO.setCoverageAmount(policy.getCoverageAmount());
	      policyDTO.setDuration(policy.getDuration());
	      policyDTO.setStartDate(policy.getStartDate());
	      policyDTO.setEndDate(policy.getEndDate());
	      policyDTO.setPolicyId(policy.getPolicyId());
	      policyDTO.setPolicyNumber(policy.getPolicyNumber());
	      policyDTO.setPremium(policy.getPremium());
	      when(userRepo.findById(1)).thenReturn(Optional.of(user));
	      when(policyMapper.convertFromDTO(policyDTO)).thenReturn(policy);
	      when(policyRepo.save(policy)).thenReturn(policy);
	      when(policyMapper.convertToDTO(policy)).thenReturn(policyDTO);	      	      
	      // When
	      PolicyDTO addedPolicyDTO = policyService.savePolicy(policy.getPolicyId(), policyDTO);
	      // Then
	      Assertions.assertNotNull(addedPolicyDTO); // Ensure addedUserDTO is not null
	  }
    @Test
	void deletePolicy_should_return_true() {
		//Given
    	int id = 2;    	
        Policy policy= Mockito.mock(Policy.class);
        Users user=Mockito.mock(Users.class);
        Transaction transaction =Mockito.mock(Transaction.class);
        Payment payment =Mockito.mock(Payment.class);
        Beneficiary beneficiary =Mockito.mock(Beneficiary.class);
        List<Users> use = new ArrayList<>();
        use.add(user);
		List<Transaction> trans = new ArrayList<>();
		trans.add(transaction);
		List<Payment> pay = new ArrayList<>();
		pay.add(payment);		
		List<Beneficiary> benef = new ArrayList<>();
		benef.add(beneficiary);
		when(policyRepo.findById(id)).thenReturn(Optional.of(policy));
		when(policy.getUser()).thenReturn(use);
		when(policy.getTransactions()).thenReturn(trans);
		when(policy.getPayments()).thenReturn(pay);
		when(policy.getBeneficiaries()).thenReturn(benef);
		//When
		boolean isDeleted = policyService.deletePolicy(id);
		// Then
        Assertions.assertTrue(isDeleted);        
	}
}