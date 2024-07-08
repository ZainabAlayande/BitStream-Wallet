package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.responses.SendBitcoinResponse;

import java.util.List;

public interface WalletService {

    SendBitcoinResponse sendBTC(SendBitcoinRequest request);
    ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request);
    List walletHistory();


