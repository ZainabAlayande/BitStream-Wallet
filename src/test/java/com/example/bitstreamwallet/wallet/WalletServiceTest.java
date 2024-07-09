package com.example.bitstreamwallet.wallet;

import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import org.bitcoinj.store.BlockStoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {

    private WalletService walletService;


    @Test
    public void testCreateWallet() throws BlockStoreException {
        WalletCreationRequest request = new WalletCreationRequest();
        WalletCreationResponse response = walletService.createWallet(request);

        assertNotNull(response);
        assertNotNull(response.getWalletId());
        assertNotNull(response.getSeedPhrase());
        assertNotNull(response.getFreshAddress());
        assertNotNull(response.getCurrentAddress());
        assertEquals(response.getFreshAddress(), response.getCurrentAddress());
        assertTrue(response.getBalance() >= 0);
    }

}
