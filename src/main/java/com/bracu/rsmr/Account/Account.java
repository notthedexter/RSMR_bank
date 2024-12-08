package com.bracu.rsmr.Account;

import com.bracu.rsmr.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double balance;
    private boolean disabled;
    private Long nId;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(Double balance, boolean disabled, Long nId) {
        this.balance = balance;
        this.disabled = disabled;
        this.nId = nId;
    }
}