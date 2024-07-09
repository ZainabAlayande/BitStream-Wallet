package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import com.example.bitstreamwallet.services.wallet.WalletService;
import com.example.bitstreamwallet.services.wallet.WalletServiceImpl;
import org.bitcoinj.store.BlockStoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        WalletCreationResponse response = walletService.createWallet(request);

        assertNotNull(response);
        assertNotNull(response.getWalletId());
        assertNotNull(response.getSeedPhrase());
        assertNotNull(response.getFreshAddress());
        assertNotNull(response.getCurrentAddress());
        assertTrue(response.getBalance() >= 0);
    }

}
