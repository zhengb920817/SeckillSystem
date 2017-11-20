package org.seckill.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class AesUtil {

	public static void main(String[] args) {
		System.out.println(aesEncrypt("root", "123456"));
		System.out.println(aesEncrypt("123456", "123456"));
	}
	
	public static String aesDecrypt(String sourceStr, String password) {

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

			keyGenerator.init(128, new SecureRandom(password.getBytes()));

			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodeFormat = secretKey.getEncoded();

			SecretKeySpec keySpec = new SecretKeySpec(encodeFormat, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			byte[] result = cipher.doFinal(Hex.decodeHex(sourceStr.toCharArray()));
			return new String(result, "utf-8");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String aesEncrypt(String sourceStr, String password) {

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

			keyGenerator.init(128, new SecureRandom(password.getBytes()));

			SecretKey secretKey = keyGenerator.generateKey();

			byte[] encodeFormat = secretKey.getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(encodeFormat, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] byteContent = sourceStr.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] result = cipher.doFinal(byteContent);

			return Hex.encodeHexString(result);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
