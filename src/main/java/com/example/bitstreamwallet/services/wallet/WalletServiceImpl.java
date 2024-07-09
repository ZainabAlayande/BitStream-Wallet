package com.example.bitstreamwallet.services.wallet;

import com.example.bitstreamwallet.repository.WalletRepository;
import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;

import java.security.SecureRandom;
import java.util.List;


public class WalletServiceImpl implements WalletService{

    private WalletRepository walletRepository;

    @Override
    public WalletCreationResponse createWallet(WalletCreationRequest request) throws BlockStoreException {
        try {
            TestNet3Params params = TestNet3Params.get();
            MemoryBlockStore store = new MemoryBlockStore(params);
            SecureRandom random = new SecureRandom();

            DeterministicSeed seed = new DeterministicSeed(random, 128, "passphrase");
            Wallet wallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH);

            BlockChain chain = new BlockChain(params, wallet, store);
            PeerGroup peerGroup = new PeerGroup(params, chain);
            peerGroup.addWallet(wallet);
            peerGroup.startAsync();

            Address freshAddress = wallet.freshReceiveAddress();
            Address currentAddress = wallet.currentReceiveAddress();
            System.out.println("Current Receive Address: " + currentAddress);
            System.out.println("Fresh Receive Address: " + freshAddress);

            return getWalletCreationResponse(seed, wallet, freshAddress, currentAddress);

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BlockStoreException("Error creating wallet", exception);
        }
    }

    private static WalletCreationResponse getWalletCreationResponse(DeterministicSeed seed, Wallet wallet, Address freshAddress, Address currentAddress) {
        WalletCreationResponse response = new WalletCreationResponse();
        response.setWalletId(freshAddress.toString());
        response.setSeedPhrase(seed.getMnemonicCode().toString());
        response.setFreshAddress(freshAddress.toString());
        response.setCurrentAddress(currentAddress.toString());
        response.setCreationTimestamp(wallet.getEarliestKeyCreationTime());
        response.setBalance(wallet.getBalance().value);
        return response;
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
