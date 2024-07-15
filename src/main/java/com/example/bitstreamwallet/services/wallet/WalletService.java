package com.example.bitstreamwallet.services.wallet;

import com.example.bitstreamwallet.models.Transaction;
import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.store.BlockStoreException;

import java.io.IOException;
import java.util.List;

public interface WalletService {

    WalletCreationResponse createWallet(WalletCreationRequest request) throws BlockStoreException;

    SendBitcoinResponse sendBTC(SendBitcoinRequest request);

    ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request) throws IOException, MnemonicException.MnemonicLengthException;

    List<Transaction> walletHistory();

    String generateBech32Address() throws MnemonicException.MnemonicLengthException, IOException;

}
