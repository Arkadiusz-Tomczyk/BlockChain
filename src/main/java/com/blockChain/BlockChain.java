package com.blockChain;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class BlockChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    public static void main(String[] args ) {

        blockChain.add(new Block("Hi I am the first block", "0"));
        blockChain.add(new Block("second block is here", blockChain.get(blockChain.size()-1).hash));
        blockChain.add(new Block("third block", blockChain.get(blockChain.size()-1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockchainJson);
    }

}
