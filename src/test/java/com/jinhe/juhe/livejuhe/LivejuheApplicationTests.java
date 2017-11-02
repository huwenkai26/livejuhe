package com.jinhe.juhe.livejuhe;

import com.jinhe.juhe.livejuhe.utils.DESUtil;
import com.jinhe.juhe.livejuhe.utils.HMACSHA256Utils;
import com.jinhe.juhe.livejuhe.utils.HttpUtils;
import com.jinhe.juhe.livejuhe.utils.Illllllll;
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

		String decrypt = DESUtil.decrypt("sQhzkpalix9zjEaMXgU7I7Zus997QnzVPKJxCmIR1bci+Qf2sMQPcDW6QoSD2whuqwhKc+WlKsJa2mmAElwtDwK04wpXc+rq","!ln1j2Z9");
//		String encode = URLEncoder.encode(decrypt);
//		System.out.println(encode);
//		String hmacsha256 = HMACSHA256Utils.HMACSHA256("277android1.1.01506925205436".getBytes(), "c2cc2c0f4efc4d3e9e3d4284b957edbf".getBytes());
		System.out.println(decrypt);

	}


}
