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
		String encrypt = DESUtil.decrypt("sQhzkpalix+RARpV0kjaP57/0HD0TPNnTmgH8+LPJDQ49spUMci0HLUSgCbWL7dWDZOboU8I1bX+C/C8jqD/yH6uiaj2X4dniuJXy2PYeiVUIAdyEGkUTWb1azpi0kt5IaJLGfJRo1c5Gwkl92B+6rG+cR2ZHHixE6n0pqu7Yqq/JROcq4QfbLRHRkKdPeApcMTB8ugNA1wQ1m6/Bu+H5vLf2+hz0xvE/6C1Hsiau24sfLx01ThMOpj+TsAwN8lUtBSuW5K+3xCcFzDG4tbPyAiwuGSsHA+/bbXf/qxfKeezqm0mWdNuJbeR51OoRdaW+5leX1Z7ssj/TGLk1P624oriV8tj2HolLSOGMI9tKISjARBx3XzQi1xPExkVqQvXeexmJsOTqFz/52XOR7ZLG24uYSj3kgyMoA2MHGT6LObrXzhz+xgEEYs7pLK5Xx6EWu9KRGbJ3dgn6zjK56YBKnlpQb7NDgKRKQAULtI5wfFeZpYXiZWkTIZtHpuB1rf7EZ6+olr/5qo=", "!ln1j2Z9");
		String encode = (encrypt);


		System.out.println(encode);


	}


}
