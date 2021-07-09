package com.secondapp.Managements.Encryption;
import java.security.MessageDigest;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;


public class HashingFunctions {

    public static String getHash(byte[] inputBytes, String algorithm){
        String hashvalue =  "";
        try {
             MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
             messageDigest.update(inputBytes);
             byte[] digestBytes = messageDigest.digest();
             hashvalue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase(Locale.ROOT);

        }catch (Exception e){
            System.out.println(String.format("hash error %s" , e.getMessage()));
        }
        return hashvalue;
    }
}
