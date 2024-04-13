package com.example.insurance.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.example.insurance.dto.PaymentDTO;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Users;
import com.example.insurance.repository.PaymentRepository;
import com.example.insurance.repository.UsersRepository;
import com.example.insurance.exception.ResourceNotFoundException;
import com.example.insurance.mapper.PaymentMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

	PaymentRepository paymentRepo;			 
	UsersRepository userRepo;				
	PaymentMapper paymentMapper;
	@Override
	public PaymentDTO savePayment(PaymentDTO paymentdto) 
	{
		Payment payment=paymentMapper.convertFromDTO(paymentdto);
		return paymentMapper.convertToDTO(paymentRepo.save(payment));
	}

	@Override
	public List<PaymentDTO> viewAllPaymentDetails()
	{
		List<Payment> payments=paymentRepo.findAll();

		return payments.stream()
                .map(paymentMapper::convertToDTO)
                .collect(Collectors.toList());
	}

	@Override
	public List<PaymentDTO> getByPaymentUserId(int userId)
	{
		Optional<Users> u = userRepo.findById(userId);
		if (u.isPresent())
		{
			List<Payment> payments=u.get().getPayments();
			return payments.stream()
					.map(paymentMapper::convertToDTO)
					.collect(Collectors.toList());
		}
		else {
			throw new ResourceNotFoundException("Id Not Found");
		}
	}

}
