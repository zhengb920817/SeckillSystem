package org.seckill.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	public static String base64Encode(final String soucrStr) {
		byte[] encodeBytes = Base64.encodeBase64(soucrStr.getBytes());
		String encodeStr = new String(encodeBytes);
		return encodeStr;
	}

	public static String base64Decode(final String sourceStr) {
		byte[] decodeBytes = Base64.decodeBase64(sourceStr);
		String decodeStr = new String(decodeBytes);
		return decodeStr;
	}
}
