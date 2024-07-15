package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import com.example.bitstreamwallet.models.WalletType;
import com.example.bitstreamwallet.services.wallet.WalletService;
import com.example.bitstreamwallet.services.wallet.WalletServiceImpl;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.store.BlockStoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {

    private WalletService walletService;

    @BeforeEach
    public void setUp() {
        walletService = new WalletServiceImpl();
    }


    @Test
    @DisplayName("Test wallet can be created")
    public void testCreateWallet() throws BlockStoreException {
        WalletCreationRequest request = new WalletCreationRequest();
        request.setName("Zen Wallet");
        request.setWalletType(String.valueOf(WalletType.STANDARD_WALLET));
        request.setPassword("password");
        WalletCreationResponse response = walletService.createWallet(request);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getWalletId());
        assertNotNull(response.getSeedPhrase());
        assertNotNull(response.getFreshAddress());
        assertNotNull(response.getCurrentAddress());
        assertTrue(response.getBalance() >= 0);
    }

    @Test
    @DisplayName("Test bech32 address can be created")
    public void testCreateBech32Address() throws BlockStoreException {
        WalletCreationRequest request = new WalletCreationRequest();
        request.setName("Zen Wallet");
        request.setWalletType(String.valueOf(WalletType.STANDARD_WALLET));
        request.setPassword("password");
        WalletCreationResponse response = walletService.createWallet(request);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getWalletId());
        assertNotNull(response.getSeedPhrase());
        assertNotNull(response.getFreshAddress());
        assertNotNull(response.getCurrentAddress());
        assertTrue(response.getBalance() >= 0);
    }


    @Test
    @DisplayName("Test user can received btc")
    public void testReceiveBTC() throws IOException, MnemonicException.MnemonicLengthException {
        ReceiveBitcoinRequest request = new ReceiveBitcoinRequest();
        request.setAmount("0.0089");
        request.setDescription("Sending to Enoch");
        request.setExpirationTime("Never");
        ReceiveBitcoinResponse response = walletService.receiveBTC(request);
//        assertNotNull(response.getUri());
    }


    @Test
    @DisplayName("Test user can received btc")
    public void testWalletCanGenerateBech32Address() throws IOException, MnemonicException.MnemonicLengthException {
        walletService.generateBech32Address();
    }

}
