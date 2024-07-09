package com.example.bitstreamwallet.models;


import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

    private String txid;
    private int version;
    private List<TransactionInput> inputs;
    private List<TransactionOutput> outputs;
    private long lockTime;
    private List<WitnessData> witnessData;
    private int size;
    private int weight;
    private long fee;



    public static class TransactionInput {
        private String previousTransactionHash;
        private int outputIndex;
        private String scriptSig;
        private long sequenceNumber;

    }

    public static class TransactionOutput {
        private long value;
        private String scriptPubKey;

        // Getters and setters
    }

    public static class WitnessData {
        private List<String> witness;
    }

}
