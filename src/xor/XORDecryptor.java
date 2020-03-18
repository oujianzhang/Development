package xor;

public class XORDecryptor {

    public static String decryptXOR(byte[] input, byte[] key) {
        String text = "";
        for(int i = 0; i < input.length; i++)
            text += (char)(input[i] ^ (key[i%key.length]));
        return text;
    }

}
