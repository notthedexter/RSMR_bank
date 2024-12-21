package com.bracu.rsmr.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountRepository;
import com.bracu.rsmr.Transaction.Transaction;
import com.bracu.rsmr.Transaction.TransactionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void setActive(Account account, boolean active) {
        account.setActive(active);
        accountRepository.save(account);
    }

    public void approveTransaction(Long transactionId, Employee employee) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (transaction.getAmount() >= 1_000_000) {
            transaction.setEmployee(employee);
            transaction.setStatus(true);
            transactionRepository.save(transaction);
        }
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
}
