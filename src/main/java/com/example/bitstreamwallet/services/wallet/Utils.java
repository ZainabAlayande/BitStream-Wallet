package com.example.bitstreamwallet.services.wallet;

import org.bitcoinj.core.Bech32;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        // Hash the public key with SHA-256
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        byte[] sha256Hash = sha256Digest.digest(publicKey);

        // Hash the result with RIPEMD-160 using Bouncy Castle
        RIPEMD160Digest ripemd160Digest = new RIPEMD160Digest();
        ripemd160Digest.update(sha256Hash, 0, sha256Hash.length);
        byte[] ripemd160Hash = new byte[ripemd160Digest.getDigestSize()];
        ripemd160Digest.doFinal(ripemd160Hash, 0);

        // Encode the hash using Bech32
        return Bech32.encode("bc", ripemd160Hash);
    }

//    public static String getBech32Address(String publicKey) throws NoSuchAlgorithmException {
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] sha256Hash = digest.digest(publicKey.getBytes(StandardCharsets.UTF_8));
//
//        // Hash the result with RIPEMD-160
////        MessageDigest ripemd160Digest = MessageDigest.getInstance("RIPEMD160");
////        byte[] ripemd160Hash = ripemd160Digest.digest(sha256Hash);
//
//        // Hash the result with RIPEMD-160 using Bouncy Castle
//        RIPEMD160Digest ripemd160Digest = new RIPEMD160Digest();
//        ripemd160Digest.update(sha256Hash, 0, sha256Hash.length);
//        byte[] ripemd160Hash = new byte[ripemd160Digest.getDigestSize()];
//        ripemd160Digest.doFinal(ripemd160Hash, 0);
//
//        // Encode the hash using Bech32
//        return Bech32.encode(Bech32.Encoding.BECH32, "bc", ripemd160Hash);
////        return Bech32.encode("bc", ripemd160Hash);
//    }
}
