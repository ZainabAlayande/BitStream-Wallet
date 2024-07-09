package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.models.Transaction;
import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;

import java.util.List;

public interface WalletService {

    WalletCreationResponse createWallet(WalletCreationRequest request);

    SendBitcoinResponse sendBTC(SendBitcoinRequest request);

    ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request);

    List<Transaction> walletHistory();

}
