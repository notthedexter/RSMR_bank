package com.bracu.rsmr.Account;

import java.rmi.AccessException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

import com.bracu.rsmr.Transaction.TransactionRepository;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Transaction.Transaction;
import com.bracu.rsmr.Transaction.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;


    public boolean createAccount(Account account) {
        account.setAccountId(UUID.randomUUID().toString());
        accountRepository.save(account);
        return true;
    }


    public void transferAmount(String fromAccountId, String toAccountId, double amount) throws Exception {
        Account fromAccount = accountRepository.findByAccountId(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException());
        Account toAccount = accountRepository.findByAccountId(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException());

        if (fromAccount.getBalance() < amount) {
            throw new AccessException("Insufficient Balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
        transactionService.createTransaction(transaction);

        accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
    }

    public void transferAmount(Transaction transaction) throws Exception {
        transferAmount(transaction.getSrcId(), transaction.getDstId(), transaction.getAmount());
    }

    public Double getAccountBalance(String accountId) throws AccountException {
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        return account.getBalance();
    }

    public void activateAccount(String accountId) throws Exception {
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
        account.setActive(true);
        accountRepository.save(account);
    }
    public void deactivateAccount(String accountId) throws Exception {

    }




}
