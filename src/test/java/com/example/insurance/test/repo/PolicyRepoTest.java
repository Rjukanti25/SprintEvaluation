package com.example.insurance.test.repo;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.example.insurance.entity.Policy;
import com.example.insurance.repository.PolicyRepository;
 
import org.junit.jupiter.api.Assertions;

//The @DataJpaTest annotation is used to test JPA repositories in Spring Boot applications. It's a specialized test annotation that provides a minimal Spring context for testing the persistence layer. This annotation can be used in conjunction with other testing annotations like @RunWith and @SpringBootTest
@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PolicyRepoTest {
	@Autowired
	PolicyRepository policyRepo;
	 @Test
     void findAll_should_return_policy_list() {
         // When
         List<Policy> policies = this.policyRepo.findAll();
         // Then
         Assertions.assertEquals(8, policies.size());
     }
	 @Test
     void findById_should_return_policy() {
         // When
         Optional<Policy> policies = this.policyRepo.findById(6);
         // Then
         Assertions.assertTrue(policies.isPresent());
     }
	 @Test
     void should_insert_new_policy() {
         // Given
		 Policy newPolicy= new Policy();
         newPolicy.setPolicyNumber(10);
         newPolicy.setCoverageAmount(500000);
         newPolicy.setDuration(36);
         newPolicy.setStartDate(LocalDate.now());
         newPolicy.setEndDate(LocalDate.of(2036, 04, 15));
         newPolicy.setPremium(2000);
         // When
         Policy persistedPolicy= this.policyRepo.save(newPolicy);
         // Then
         Assertions.assertNotNull(persistedPolicy);
 
     }
	 @Test
     void should_update_existing_policy() {
         // Given
         Policy existingPolicy= new Policy();
         existingPolicy.setPolicyId(6);
         existingPolicy.setPolicyNumber(10);
         existingPolicy.setCoverageAmount(500000);
         existingPolicy.setDuration(36);
         existingPolicy.setStartDate(LocalDate.now());
         existingPolicy.setEndDate(LocalDate.of(2036, 04, 15));
         existingPolicy.setPremium(2000);
         // When
         Policy updatedPolicy= this.policyRepo.save(existingPolicy);
         // Then
         Assertions.assertNotNull(updatedPolicy);
         Assertions.assertEquals(6, updatedPolicy.getPolicyId());
         Assertions.assertEquals(10, updatedPolicy.getPolicyNumber());
         Assertions.assertEquals(500000, updatedPolicy.getCoverageAmount());
         Assertions.assertEquals(36, updatedPolicy.getDuration());
         Assertions.assertEquals(LocalDate.now(),updatedPolicy.getStartDate());
         Assertions.assertEquals(LocalDate.of(2036, 04, 15), updatedPolicy.getEndDate());
         Assertions.assertEquals(2000, updatedPolicy.getPremium());
     }
	 @Test
     void deleteById_should_delete_policy() {
         // When
         this.policyRepo.deleteById(2);
         Optional<Policy> policies= this.policyRepo.findById(2);
         // Then
         Assertions.assertFalse(policies.isPresent());
     }
}