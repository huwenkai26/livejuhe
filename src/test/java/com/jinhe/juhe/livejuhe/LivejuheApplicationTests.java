package com.jinhe.juhe.livejuhe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jinhe.juhe.livejuhe.utils.JavaCallJsUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivejuheApplicationTests {

    private String timestamp;
    private String sign;

    @Test
    public void contextLoads() {
    }

    private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

//    @Autowired    
//    private MockHttpSession session;// 注入模拟的http session    
//        
//    @Autowired    
//    private MockHttpServletRequest request;// 注入模拟的http request\    

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testQ1() throws Exception {

        for (int i = 0; i < 100; i++) {

            MvcResult result = mockMvc.perform(get("/paopao/login"))
                    .andExpect(status().isOk())// 模拟向testRest发送get请求
                    .andReturn();// 返回执行请求的结果

            System.out.println(result.getResponse().getContentAsString());
            Thread.sleep(60 * 1000 * 2);
        }

    }

    @Test
    public void testSave() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("address", "合肥");
        map.put("name", "测试");
        map.put("age", 50);

        MvcResult result = mockMvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(post("/page").param("pageNo", "1").param("pageSize", "2"))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testShow() throws Exception {//	System.out.println(sign);


        long timeMillis = System.currentTimeMillis();
//		String id = 277;
//		String data = id + "android1.1.0" + timeMillis;
//
//		String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
//		String	encrypt = DESUtil.decrypt("sQhzkpalix+RARpV0kjaP57/0HD0TPNnigo4i7jNa/c2xCvSegXP9iXYxNXnae6OMQOUQSdF9Qn+C/C8jqD/yH6uiaj2X4dniuJXy2PYeiVUIAdyEGkUTWb1azpi0kt5+J5IuDTNCWsLaw1OKA1OrSM3dpIkaRbir5INCCRiXiTilEz3lnMj/TpV1Fr2iMMElg+GSfZQkVygJDCRx3QzPoMsOrDcM8XC/6C1Hsiau26v0VQjEQV6zPzAEAYoHqw4lHiHfT30TTukR/j8hXZ0qFyEJ9gLMDKvkXKenRZ60OpZ2b0E+RflHwIGmj8J1pY3hn7q3IPYTNL/TGLk1P624oriV8tj2HolLSOGMI9tKISjARBx3XzQi1xPExkVqQvXeexmJsOTqFz/52XOR7ZLG24uYSj3kgyMoA2MHGT6LObrXzhz+xgEEYs7pLK5Xx6EWu9KRGbJ3dgn6zjK56YBKnlpQb7NDgKRKQAULtI5wfFeZpYXiZWkTIZtHpuB1rf7EZ6+olr/5qo=", "!ln1j2Z9");
//		String encode = URLEncoder.de(encrypt);


    }

}  