package com.blockChain;

import com.google.gson.GsonBuilder;

import java.security.Security;
import java.util.ArrayList;

public class BlockChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public  static int difficulty = 5;
    public static Wallet walletA;
    public static Wallet walletB;


    public static void main(String[] args ) {
        //setup Bouncey castle as a Security provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //Create new wallets
        walletA = new Wallet();
        walletB = new Wallet();
        //test public and private keys
        System.out.println("private and public keys: ");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
        //cerate a test transaction from walletA to walletB
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);
        //verify the signature works and verify it from the public key
        System.out.println("is signature verified");
        System.out.println(transaction.verifySignature());


//        blockChain.add(new Block("Hi I am the first block", "0"));
//        System.out.println("Trying to Mine block 1...");
//        blockChain.get(0).mineBlock(difficulty);
//
//        blockChain.add(new Block("second block is here", blockChain.get(blockChain.size()-1).hash));
//        System.out.println("Trying to Mine block 2...");
//        blockChain.get(1).mineBlock(difficulty);
//
//        blockChain.add(new Block("third block", blockChain.get(blockChain.size()-1).hash));
//        System.out.println("Trying to Mine block 3...");
//        blockChain.get(2).mineBlock(difficulty);
//
//        System.out.println("\nBlockChain is valid: " + isChainValid());
//
//        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
//        System.out.println("\nThe block chain");
//        System.out.println(blockchainJson);
    }
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i=1; i<blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            // compare registered hash and calculated it
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hash not equal");
                return false;
            }
            //check if hash i s solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
