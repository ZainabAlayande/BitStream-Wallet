package com.example.bitstreamwallet;


import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BitstreamWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitstreamWalletApplication.class, args);

        NetworkParameters params = TestNet3Params.get();
        WalletAppKit kit = new WalletAppKit(params, new java.io.File("."), "bitstream");

        kit.startAsync();
        kit.awaitRunning();

        Wallet wallet = kit.wallet();
        System.out.println("Wallet address: " + wallet.currentReceiveAddress());
    }

}
