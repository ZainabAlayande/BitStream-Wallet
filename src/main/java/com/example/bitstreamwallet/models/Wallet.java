package com.example.bitstreamwallet.models;



import java.time.LocalDateTime;


public class Wallet {

    private String name;

    private WalletType walletType;

    private String seed;

    private String password;

    private LocalDateTime createdAt;

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
