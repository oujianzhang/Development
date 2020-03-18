package xor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {

    private static final String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator;

    public static byte[] getCiphertext() throws IOException {
        String[] vals = Files.readAllLines(Paths.get(PATH + "cipher.txt")).get(0).split(",");
        byte[] cipher = new byte[vals.length];
        for(int i = 0; i < vals.length; i++)
            cipher[i] = (byte)Integer.parseInt(vals[i]);
        return cipher;
    }

}
