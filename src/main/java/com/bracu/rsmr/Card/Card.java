package com.bracu.rsmr.Card;

import com.bracu.rsmr.Account.Account;
import jakarta.persistence.*;

import java.util.Random;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardNumber;
    private String cardType; // Visa or MasterCard
    private String expirationDate;
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Card() {}

    public Card(String cardType, Account account) {
        this.cardNumber = generateUniqueCardNumber(cardType);
        this.cardType = cardType;
        this.expirationDate = generateExpirationDate();
        this.cvv = generateCVV();
        this.account = account;
    }

    private String generateUniqueCardNumber(String cardType) {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder(cardType.equals("Visa") ? "4" : "5");
        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private String generateExpirationDate() {
        return "12/29";
    }

    private String generateCVV() {
        Random random = new Random();
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
