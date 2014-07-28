package com.eflake.utils.library.utils;

import java.security.MessageDigest;

public class EncryptionUtils {
	private static final String TAG = "EncryptionUtils";

	private EncryptionUtils() { /* cannot be instantiated */
	}

	/**
	 * 生成加密后的密码 两次MD5加密并加入用户名字段进行混淆
	 * 
	 * @param username
	 * @param password
	 * @return String MD5
	 */
	public static String generatePassword(String username, String password) {
		return MD5(username + MD5(password).toUpperCase()).toUpperCase().trim();
	}

	/**
	 * MD5加密，32位
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
