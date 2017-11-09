package com.jinhe.juhe.livejuhe.utils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.util.Map;
import java.util.Set;

public class JavaCallJsUtils {


    public static String JavaCallJsDecrypt(String data,String loginMd5) {
        // 得到一个ScriptEngine对象
        ScriptEngineManager maneger = new ScriptEngineManager();
        ScriptEngine engine = maneger.getEngineByName("JavaScript");

        // 读js文件

        Reader scriptReader = null;
        try {

            String file = JavaCallJsUtils.class.getClassLoader().getResource("popocCryptoUtils.js").getFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            scriptReader = new InputStreamReader(fileInputStream, "utf-8");
            engine.eval(scriptReader);
            if (engine instanceof Invocable) {
                // 调用JS方法
                Invocable invocable = (Invocable) engine;
                String result = (String) invocable.invokeFunction("decrypt",data,loginMd5);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scriptReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static Set<Map.Entry<String, Object>> JavaCallJsGetGsign(String loginMd5) {
        // 得到一个ScriptEngine对象
        ScriptEngineManager maneger = new ScriptEngineManager();
        ScriptEngine engine = maneger.getEngineByName("JavaScript");

        // 读js文件

        Reader scriptReader = null;
        try {
            String file = JavaCallJsUtils.class.getClassLoader().getResource("popocCryptoUtils.js").getFile();

            FileInputStream fileInputStream = new FileInputStream(file);
            scriptReader = new InputStreamReader(fileInputStream, "utf-8");
            engine.eval(scriptReader);
            if (engine instanceof Invocable) {
                // 调用JS方法
                Invocable invocable = (Invocable) engine;
                ScriptObjectMirror result = (ScriptObjectMirror) invocable.invokeFunction("getSign",loginMd5);
                Set<Map.Entry<String, Object>> entries = result.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    System.out.println(entry.toString());
                }
                return entries;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scriptReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
