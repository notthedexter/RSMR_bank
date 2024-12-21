package com.bracu.rsmr.Card;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Card createCard(String cardType, String accountId) throws Exception {
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        Card card = new Card(cardType, account);
        account.getCards().add(card);
        return cardRepository.save(card);
    }

    public List<Card> getCardsByAccount(String accountId) throws Exception {
        return cardRepository.findByAccountId(Long.valueOf(accountId));
    }
}
