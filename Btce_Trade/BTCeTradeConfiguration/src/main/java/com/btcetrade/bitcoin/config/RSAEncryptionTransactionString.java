package com.btcetrade.bitcoin.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAEncryptionTransactionString {

	
	public ObjectEncryption getEncryptTransaction(String txnId) throws Exception{
		Map<String, Object> keys = getRSAKeys();

        PrivateKey privateKey = (PrivateKey) keys.get("private");
        PublicKey publicKey = (PublicKey) keys.get("public");

        String encryptedText = encryptMessage(txnId, privateKey);
        String descryptedText = decryptMessage(encryptedText, publicKey);

        System.out.println("input:" + txnId);
        System.out.println("encrypted:" + encryptedText);
        System.out.println("decrypted:" + descryptedText);
        ObjectEncryption encrypt= new ObjectEncryption();
        encrypt.setEncrypt(encryptedText);
        encrypt.setDcrypt(descryptedText);
        return encrypt;
	}


	


	private Map<String, Object> getRSAKeys() throws Exception {
	
	     KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	        keyPairGenerator.initialize(2048);
	        KeyPair keyPair = keyPairGenerator.generateKeyPair();
	        PrivateKey privateKey = keyPair.getPrivate();
	        PublicKey publicKey = keyPair.getPublic();

	        Map<String, Object> keys = new HashMap<String,Object>();
	        keys.put("private", privateKey);
	        keys.put("public", publicKey);
	        return keys;

	}

	private String encryptMessage(String txnId, PrivateKey privateKey) throws Exception{
		    Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(txnId.getBytes()));
	}
	
	private String decryptMessage(String encryptedText, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
	}


}
