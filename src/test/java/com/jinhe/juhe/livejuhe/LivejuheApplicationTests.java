package com.jinhe.juhe.livejuhe;

import com.jinhe.juhe.livejuhe.utils.*;
import com.sun.corba.se.impl.encoding.CodeSetConversion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class LivejuheApplicationTests {
	private static String url = "http://api.jushiyaoye.com/apiv2/queryZhiBoIndex";
	@Test
	public void contextLoads() {

	}

	@Test
	public void testShow() throws Exception {//	System.out.println(sign);
//		try {
//
//
//			String sss = Illllllll.Wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww(new byte[]{(byte) 94, (byte) 9, (byte) 95, (byte) 12, (byte) 70, (byte) 86, (byte) 66, (byte) 13, (byte) 80, (byte) 21, (byte) 90, (byte) 76, (byte) 84, (byte) 11}, "7c4a59");
//
//
//
//			byte[] bytes = "c2cc2c0f4efc4d3e9e3d4284b957edbf".getBytes();
//
//			String decrypt = DESUtil.encrypt("{\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1509592879532\",\"v\":\"342c6708f105b19433f5192b69017a8a672c5dc5620d091c6e91bb50046a6306\"}","!ln1j2Z9");
//			System.out.println(decrypt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		long timeMillis = System.currentTimeMillis();


//		String data = "277103095android1.1.01506996174750";
//
//		String key = "d6dffc12cff144899cabd1a90df02a6f";
//
//		String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
//		System.out.println(sha256);
//		String encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506996757046\",\"v\":\"2fc5a3f5e78e9064d2c138f2fe7824252bf79b56c35c622d7fc62abb5ea19338\"}", "!ln1j2Z9");
//		String encode = (encrypt);
//
//
//		System.out.println(encode);

		String date2String = DateUtil.getDate2String("2017-11-03 1517:43:11");
		System.out.println(date2String);

	}


}
