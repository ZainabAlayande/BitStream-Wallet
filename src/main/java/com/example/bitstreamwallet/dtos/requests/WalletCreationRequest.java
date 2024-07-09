package com.example.bitstreamwallet.dtos.requests;

import com.example.bitstreamwallet.models.WalletType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Builder
public class WalletCreationRequest {

    private String name;

    private String walletType;

    private String password;


}
