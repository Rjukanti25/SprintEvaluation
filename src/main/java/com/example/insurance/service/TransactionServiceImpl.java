package com.example.insurance.service;

import com.example.insurance.dto.TransactionDTO;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Transaction;
import com.example.insurance.entity.Users;
import com.example.insurance.exception.PaymentsNotFoundException;
import com.example.insurance.mapper.TransactionMapper;
import com.example.insurance.repository.PaymentRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.TransactionRepository;
import com.example.insurance.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UsersRepository usersRepository;
    private final PolicyRepository policyRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.transactionDTOToTransaction(transactionDTO);

        Users user = usersRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new PaymentsNotFoundException("User not found with ID: " + transactionDTO.getUserId()));
        Policy policy = policyRepository.findById(transactionDTO.getPolicyId())
                .orElseThrow(() -> new PaymentsNotFoundException("Policy not found with ID: " + transactionDTO.getPolicyId()));
        Payment payment = paymentRepository.findById(transactionDTO.getPaymentId())
                .orElseThrow(() -> new PaymentsNotFoundException("Payment not found with ID: " + transactionDTO.getPaymentId()));

        transaction.setUser(user);
        transaction.setPolicies(policy);
        transaction.setPayments(payment);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDTO(savedTransaction);
    }

    @Override
    public TransactionDTO getTransactionById(int transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new PaymentsNotFoundException("Transaction not found with ID: " + transactionId));
        return transactionMapper.transactionToTransactionDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::transactionToTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionDTO.getTransactionId())
                .orElseThrow(() -> new PaymentsNotFoundException("Transaction not found with ID: " + transactionDTO.getTransactionId()));

        // Update transaction fields here based on transactionDTO
        // For simplicity, let's assume updating is handled similarly to adding
    
        transaction.setStatus(transactionDTO.getStatus());
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDTO(updatedTransaction);
}

}
