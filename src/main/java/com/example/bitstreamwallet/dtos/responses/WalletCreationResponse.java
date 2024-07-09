package com.example.bitstreamwallet.dtos.responses;

import lombok.*;

//@Builder
@Getter
@Setter
//@AllArgsConstructor
//@RequiredArgsConstructor
public class WalletCreationResponse {

    private String walletId;
    private String seedPhrase;
    private String freshAddress;
    private String currentAddress;
    private long creationTimestamp;
    private long balance;


}
