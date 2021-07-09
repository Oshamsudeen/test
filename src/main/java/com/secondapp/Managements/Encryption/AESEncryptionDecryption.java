package com.secondapp.Managements.Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESEncryptionDecryption {
   private static SecretKeySpec secretKey;
   private static byte[] key;
   private static final String ALGORITHM = "AES";

   public static void prepareSecreteKey(String mykey){
      MessageDigest sha = null;
      try {
         key = mykey.getBytes(StandardCharsets.UTF_8);
         sha = MessageDigest.getInstance("SHA-1");
         key = sha.digest();
         key = Arrays.copyOf(key,16);
         secretKey = new SecretKeySpec(key, ALGORITHM);
      }catch (NoSuchAlgorithmException e){
          e.printStackTrace();
      }
   }

   public static String encrypt(String strToEncrypt, String secret){
      String encrypted = "";
      try{
         prepareSecreteKey(secret);
         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
         encrypted =  Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
      }catch (Exception e){
         System.out.println("Error while encrypting: " + e.toString());
      }
      System.out.println(" encrypting: " + encrypted);
      return encrypted;
   }

   public static String decrypt(String strToDecrypt, String secret) {
      try {
         prepareSecreteKey(secret);
         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE, secretKey);
         return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
      } catch (Exception e) {
         System.out.println("Error while decrypting: " + e.toString());
      }
      return null;
   }

   private static final String cbckey = "aesEncryptionKey";
   private static final String initVector = "encryptionIntVec";

   public static String encrypt(String value) {
      try {

         IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
         SecretKeySpec skeySpec = new SecretKeySpec(cbckey.getBytes("UTF-8"), "AES");

         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
         return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public static String decrypt(String value){
      try{
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(cbckey.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
      return new String(cipher.doFinal(Base64.getDecoder().decode(value)));
   } catch (Exception ex) {
      ex.printStackTrace();
   }

    return null;
   }
}
