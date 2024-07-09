package com.example.bitstreamwallet;


import com.google.common.base.Joiner;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;


public class BitstreamWalletApplication {

    public static void main(String[] args) throws UnreadableWalletException {
        NetworkParameters params = TestNet3Params.get();
        WalletAppKit kit = new WalletAppKit(params, new java.io.File("."), "bitstream");

        kit.startAsync();
        kit.awaitRunning();

        Wallet wallet = kit.wallet();
        System.out.println("Wallet address: " + wallet.currentReceiveAddress());

        DeterministicSeed deterministicSeed = wallet.getKeyChainSeed();
        System.out.println("Seed words are: " + Joiner.on(" ").join(deterministicSeed.getMnemonicCode()));
        System.out.println("Seed birthday is: " + deterministicSeed.getCreationTimeSeconds());

        String seedCode = "yard impulse luxury drive today throw farm pepper survey wreck glass federal";
        long creationtime = 1409478661L;
        DeterministicSeed seed = new DeterministicSeed(seedCode, null, "", creationtime);
        Wallet restoredWallet = Wallet.fromSeed(params, seed);
    }

}
