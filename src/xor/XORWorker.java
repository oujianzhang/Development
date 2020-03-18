package xor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class XORWorker implements Callable<String> {

    private static List<String> dictionary = new ArrayList<>();
    static {
        String[] words = "the,be,to,of,and,in,that,have".split(",");
        for(String word : words)
            dictionary.add(word);
    }

    private static int cnt = 0;

    private byte[] chars;
    private byte[] input;

    private boolean running = true;

    public XORWorker(byte start, byte[] input) {
        chars = new byte[] {start, 'a', 'a'};
        this.input = input;
    }

    private static boolean checkResult(String input) {
        String[] words = input.split(" ");
        int wordcount = 0;
        for(String word : words) {
            if(dictionary.contains(word))
                wordcount++;
        }
        return wordcount > 10;
    }

    private boolean increaseKeyValue() {
        chars[2]++;
        if(chars[2] > 'z') {
            chars[2] = 'a';
            chars[1]++;
            if(chars[1] > 'z')
                return false;
        }
        return true;
    }

    @Override
    public String call() throws Exception {
        while(running) {
            String dec = XORDecryptor.decryptXOR(input, chars);
            if(checkResult(dec))
                return "" + (char)chars[0] + (char)chars[1] + (char)chars[2];
            running = increaseKeyValue();
        }
        throw new Exception("Could not find key.");
    }

}
