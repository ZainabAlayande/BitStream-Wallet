package com.example.bitstreamwallet.services.wallet;

import com.example.bitstreamwallet.dtos.requests.ReceiveBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.SendBitcoinRequest;
import com.example.bitstreamwallet.dtos.requests.WalletCreationRequest;
import com.example.bitstreamwallet.dtos.responses.ReceiveBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.SendBitcoinResponse;
import com.example.bitstreamwallet.dtos.responses.WalletCreationResponse;
import com.example.bitstreamwallet.models.Transaction;
import com.example.bitstreamwallet.repository.WalletRepository;
import org.bitcoinj.core.*;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.uri.BitcoinURI;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import static com.example.bitstreamwallet.services.wallet.Utils.*;


public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private Wallet wallet;

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
    public ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request) throws Exception {
        validateRequestIsValid(request);
//        String bech32Address = generateBech32Address();
        String bech32Address = publicKey();
        var bitcoinUri = BitcoinURI.convertToBitcoinURI(Address.fromString(TestNet3Params.get(), bech32Address), Coin.FIFTY_COINS, "Zeni", "New coin of mine");
        System.out.println("Bitcoin Uri = " + bitcoinUri);
        return null;
    }

    @Override
    public String generateBech32Address() throws Exception {
        //Generate a seed
        MnemonicCode mnemonic = new MnemonicCode();
        SecureRandom random = new SecureRandom();
        byte[] entropy = new byte[32];
        random.nextBytes(entropy);

        List<String> mnemonicMnemonic = mnemonic.toMnemonic(entropy);
        byte[] seed = MnemonicCode.toSeed(mnemonicMnemonic, "zen");

        // Generate master private key
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);

        // Derive child key (account 0, external chain, address index 0)
        List<ChildNumber> path = HDPath.M().extend(ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        DeterministicKey childKey = new DeterministicHierarchy(masterPrivateKey).deriveChild(path, false, true, new ChildNumber(0, false));

        // Get the Bech32 address from the public key
        byte[] childPublicKey = childKey.getPubKey();
        System.out.println(Arrays.toString(childPublicKey));
        String bech32Address = getBech32Address(childPublicKey);
        System.out.println("Bech32 Address: " + bech32Address);
        return bech32Address;
    }

    private void validateRequestIsValid(ReceiveBitcoinRequest request) {

    }


    @Override
    public List walletHistory() {

        return null;
    }
}
