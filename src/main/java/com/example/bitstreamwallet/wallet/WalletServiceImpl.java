package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.models.Wallet;
import com.example.bitstreamwallet.repository.WalletRepository;
import com.example.bitstreamwallet.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.requests.WalletCreationRequest;
import com.example.bitstreamwallet.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.responses.WalletCreationResponse;
import org.bitcoinj.core.ECKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public WalletCreationResponse createWallet(WalletCreationRequest request) {
        return null;
    }

    @Override
    public SendBitcoinResponse sendBTC(SendBitcoinRequest request) {
        ECKey key = new ECKey();
        String privateKey = key.getPrivateKeyAsWiF();
        String publicKey = key.getPublicKeyAsHex();

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
