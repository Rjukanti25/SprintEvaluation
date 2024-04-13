package com.example.insurance.service;

import java.util.List;

import com.example.insurance.dto.PaymentDTO;

public interface PaymentService {
    public PaymentDTO savePayment(PaymentDTO paymentDTO);
    public List<PaymentDTO> viewAllPaymentDetails();
	public List<PaymentDTO> getByPaymentUserId(int userId);

}
