package com.example.bitstreamwallet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Wallet {

    private String name;

    private WalletType walletType;

    private String seed;

    private String password;

    private LocalDateTime createdAt;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
