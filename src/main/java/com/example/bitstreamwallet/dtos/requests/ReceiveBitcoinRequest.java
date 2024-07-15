package com.example.bitstreamwallet.dtos.requests;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ReceiveBitcoinRequest {

    private String amount;
    private String description;
    private String expirationTime;

}
