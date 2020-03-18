package xor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class XORCracker {

    public static void main(String[] args) {
        byte[] input = new byte[0];
        try {
            input = FileIO.getCiphertext();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Callable<String>> workers = new LinkedList<>();
        for(byte i = 'a'; i <= 'z'; i++)
            workers.add(new XORWorker(i, input));
        String key = null;
        while(key == null) {
            try {
                key = pool.invokeAny(workers);
            } catch (Exception e) { }
        }
        pool.shutdown();

        long end = System.currentTimeMillis();

        System.out.println("Found key: " + key);
        String txt = XORDecryptor.decryptXOR(input, key.getBytes());
        System.out.println(txt);

        int sum = 0;
        for(int i = 0; i < txt.length(); i++)
            sum += (int)txt.charAt(i);
        System.out.println();
        System.out.println("Sum of ASCII Values: " + sum);
    }

}
