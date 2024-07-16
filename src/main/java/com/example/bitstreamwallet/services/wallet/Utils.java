package com.example.bitstreamwallet.services.wallet;

import org.bitcoinj.core.Bech32;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Security;

public class Utils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getBech32Address(byte[] publicKey) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha256Hash = sha256.digest(publicKey);

        MessageDigest ripemd160 = MessageDigest.getInstance("RIPEMD160");
        byte[] ripemd160Hash = ripemd160.digest(sha256Hash);

        String hrp = "bc";
        return Bech32.encode(Bech32.Encoding.BECH32, hrp, ripemd160Hash);
    }

    public static String publicKey()  {
        ECKey key = new ECKey();
        byte[] publicKey = key.getPubKey();
        return bytesToString(publicKey);
    }

    public static String privateKey()  {
        ECKey key = new ECKey();
        BigInteger publicKey = key.getPrivKey();
        return String.valueOf(publicKey);
    }




    public static boolean isValidBech32Address(String address) {
        try {
            Bech32.Bech32Data bech32Data = Bech32.decode(address);

            if (!bech32Data.hrp.equals("bc")) {
                return false;
            } else {
                return true;
            }
        } catch (Exception exception) {
            return false;
        }
    }


}
