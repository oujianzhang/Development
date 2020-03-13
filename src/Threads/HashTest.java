package Threads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class HashTest {
    
    
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String password = "Lamar" + "Shinton" + "9j0g3";
            byte[] hash = md.digest(password.getBytes());
            String hashAsHexString = DatatypeConverter.printHexBinary(hash);
            System.out.println(hashAsHexString);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
