package com.wright.utils;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.util.encoders.Base64;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String s = AesCbcUtil.encrypt("b34118dada7824ae3eb80f417a292608");
			System.out.println(s);
			System.out.println(AesCbcUtil.decrypt(s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
