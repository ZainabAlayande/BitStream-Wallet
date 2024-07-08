package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.models.Transaction;
import com.example.bitstreamwallet.models.Wallet;
import com.example.bitstreamwallet.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.requests.WalletCreationRequest;
import com.example.bitstreamwallet.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.responses.WalletCreationResponse;

import java.util.List;

public interface WalletService {

    WalletCreationResponse createWallet(WalletCreationRequest request);
    SendBitcoinResponse sendBTC(SendBitcoinRequest request);
    ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request);
    List<Transaction> walletHistory();

