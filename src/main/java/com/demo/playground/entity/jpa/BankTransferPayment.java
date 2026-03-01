package com.demo.playground.entity.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_bank_transfer_payment")
@Getter
@Setter
@NoArgsConstructor
public class BankTransferPayment extends Payment {

    private String bankAccountId;

    public BankTransferPayment(double amount, String bankAccountId) {
        super(amount);
        this.bankAccountId = bankAccountId;
    }
}
