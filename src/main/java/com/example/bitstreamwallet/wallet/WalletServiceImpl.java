package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.repository.WalletRepository;
import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import org.bitcoinj.core.ECKey;

import java.util.List;


public class WalletServiceImpl implements WalletService{

    private WalletRepository walletRepository;

    @Override
    public WalletCreationResponse createWallet(WalletCreationRequest request) {
        return null;
    }

    @Override
    public SendBitcoinResponse sendBTC(SendBitcoinRequest request) {
        ECKey key = new ECKey();
//        String privateKey = key.getPrivateKeyAsWiF();
//        String publicKey = key.getPublicKeyAsHex();

        return null;
    }

    @Override
    public ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request) {
        return null;
    }


    @Override
    public List walletHistory() {

        return null;
    }
}
