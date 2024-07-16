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
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import static com.example.bitstreamwallet.services.wallet.Utils.bytesToString;
import static com.example.bitstreamwallet.services.wallet.Utils.getBech32Address;


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
    public ReceiveBitcoinResponse receiveBTC(ReceiveBitcoinRequest request) throws IOException, MnemonicException.MnemonicLengthException {
        validateRequestIsValid(request);

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
        System.out.println("Mnemonic = " + mnemonicMnemonic);

        byte[] seed = MnemonicCode.toSeed(mnemonicMnemonic, "zen");
        System.out.println("Mnemonic + Passphrase = " + bytesToString(seed));

        // Generate master private key
        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);

        // Derive child key (account 0, external chain, address index 0)
        List<ChildNumber> path = HDPath.M().extend(ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        DeterministicKey childKey = new DeterministicHierarchy(masterPrivateKey).deriveChild(path, false, true, new ChildNumber(0, false));

        // Get the Bech32 address from the public key
        String bech32Address = getBech32Address(childKey.getPubKey());
        System.out.println("Bech32 Address: " + bech32Address);

        return bech32Address;


//        // Create master private key
//        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
//        String getPrivateKey = masterPrivateKey.getPrivateKeyAsHex();
//        System.out.println("Master Priv Key = " + getPrivateKey);
//
//        // Create master public key
//        ECKey masterPublicKey = masterPrivateKey.decompress();
//        String decompressedMasterPublicKey = masterPublicKey.getPublicKeyAsHex();
//        System.out.println("Master Pub Key = " + decompressedMasterPublicKey);
//
//        // Derive the first account's extended private key
//        DeterministicHierarchy hierarchy = new DeterministicHierarchy(masterPrivateKey);
//        List<ChildNumber> accountPath = HDPath.M().extend(ChildNumber.ZERO_HARDENED);
//        DeterministicKey accountKey = hierarchy.deriveChild(accountPath, false, true, new ChildNumber(0, true));
//        var extendedPrivateKey = accountKey.getPrivateKeyAsHex();
//        System.out.println("Extended Private key = " + extendedPrivateKey);
//
//        // Derive the extended public key from the extended private key
//        ECKey accountPubKey = accountKey.decompress();
//        String extendedPublicKey = accountPubKey.getPublicKeyAsHex();
//        System.out.println("Extended Pub Key = " + extendedPublicKey);
//
//        // Derive the child private key
//        DeterministicKey childPrivateKey = HDKeyDerivation.deriveChildKey(accountKey, ChildNumber.ZERO_HARDENED);
//        String childPrivKey = childPrivateKey.getPrivateKeyAsHex();
//
//        //Derive child public key
//        String childPublicKey = childPrivateKey.getPublicKeyAsHex();
//
//        //Get Bech32 address from the childPublic Key
//        String bech32Address = getBech32Address(childPublicKey);
//        System.out.println("Bech32 Address = " + bech32Address);
//
//        return bech32Address;
    }

    private void validateRequestIsValid(ReceiveBitcoinRequest request) {
    }


    @Override
    public List walletHistory() {

        return null;
    }
}
