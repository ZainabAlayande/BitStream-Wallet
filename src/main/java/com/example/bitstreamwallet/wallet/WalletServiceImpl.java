package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.responses.SendBitcoinResponse;

import java.util.List;

public class WalletServiceImpl implements WalletService{

    @Override
    public SendBitcoinResponse sendBTC(SendBitcoinRequest request) {
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
