package com.example.bitstreamwallet;


import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;

public class BitstreamWalletApplication {

    public static void main(String[] args) {
        NetworkParameters params = TestNet3Params.get();
        WalletAppKit kit = new WalletAppKit(params, new java.io.File("."), "bitstream");

        kit.startAsync();
        kit.awaitRunning();

        Wallet wallet = kit.wallet();
        System.out.println("Wallet address: " + wallet.currentReceiveAddress());
    }

}
