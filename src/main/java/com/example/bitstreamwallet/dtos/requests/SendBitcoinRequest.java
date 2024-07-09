package com.example.bitstreamwallet.dtos.requests;

import lombok.*;

//@Builder
//@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
public class SendBitcoinRequest {

    private String receiverAddress;

    private String description;

    private String amount;
}
