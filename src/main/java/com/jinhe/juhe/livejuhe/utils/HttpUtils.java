package com.jinhe.juhe.livejuhe.utils;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Http请求工具类
 */
public class HttpUtils {
    static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8087;
    static int I;
    static String Cookie = "sails.sid=s%3AGvllE_Ne5DrL4wyTrLJjcL4FYeVM6VlU.W8ANvRjbl0WE6B2xxcfRGVhhz4xNQdGgrBRDh%2FX0KDA; Path=/; Expires=Fri, 10 Nov 2017 08:45:04 GMT; HttpOnly";
    private static String i;
    private static String Cookie1 = "sails.sid=s%3AGvllE_Ne5DrL4wyTrLJjcL4FYeVM6VlU.W8ANvRjbl0WE6B2xxcfRGVhhz4xNQdGgrBRDh%2FX0KDA; Path=/; Expires=Fri, 10 Nov 2017 08:45:04 GMT; HttpOnly";
    private static String Cookie2 = "sails.sid=s%3A5OK-mutlD-24rcJPwT4ozpzFkB-lmGlq.AGZTJHM2NO3EOBF3F23qercwHWfVJL4gJcmqoIpfkZI; Path=/; Expires=Fri, 10 Nov 2017 11:46:35 GMT; HttpOnly";

    /**
     * 编码
     *
     * @param source
     * @return
     */
    public static String urlEncode(String source, String encode) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }


    public static String urlEncodeGBK(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }

    /**
     * 发起http请求获取返回结果
     *
     * @param req_url 请求地址
     * @param ip
     * @param port    @return
     */
    public static String httpRequest(String req_url, String ip, Integer port) {

//        if(ip.isEmpty()&&port!=null){
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("113.200.214.164", 9999));
//            httpUrlConn = (HttpURLConnection)url.openConnection(proxy);
//        }else {
//            httpUrlConn = (HttpURLConnection)url.openConnection();
//        }


        StringBuffer buffer = new StringBuffer();
        try {

            URL url = new URL(req_url);
            HttpURLConnection httpUrlConn = null;
            if (!ip.isEmpty() && port != null) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
                httpUrlConn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                httpUrlConn = (HttpURLConnection) url.openConnection();
            }
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(10000);
            httpUrlConn.setReadTimeout(10000);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");
//            httpUrlConn.setRequestProperty("Cookie", "yd_cookie=c2765ac4-3cf1-4e3d091843a746ad549f6007457fcff355d4; yunsuo_session_verify=fb11026077f7ae2aa07e37f844c7d10b");


//             httpUrlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n");
//             httpUrlConn.setRequestProperty("Cookie", "yd_cookie=c2765ac4-3cf1-4e3d091843a746ad549f6007457fcff355d4; yunsuo_session_verify=fb11026077f7ae2aa07e37f844c7d10b");
//            httpUrlConn.setRequestProperty("Host", "renminren.com");
            httpUrlConn.connect();  //发送请求


            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();


        } catch (FileNotFoundException e) {

            System.out.println("1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("12");
        } catch (ProtocolException e) {
            e.printStackTrace();
            System.out.println("123");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("124");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("代理异常" + req_url);

        }
        return buffer.toString();
    }

    /**
     * 发送http请求取得返回的输入流
     *
     * @param requestUrl 请求地址
     * @return InputStream
     */
    public static InputStream httpRequestIO(String requestUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // 获得返回的输入流
            inputStream = httpUrlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url     发送请求的 URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param isproxy 是否使用代理模式
     * @param map
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, boolean isproxy, Map map) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if (isproxy) {//使用代理模式
                @SuppressWarnings("static-access")
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 打开和URL之间的连接

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法


            // 设置通用的请求属性
            if (!url.contains("index")) {
                if (map != null) {
                    conn.setRequestProperty("uuid", (String) map.get("uuid"));
                    conn.setRequestProperty("accesstoken", (String) map.get("accesstoken"));
                } else {
                    conn.setRequestProperty("uuid", "b2d40653-f3dc-4a58-a84f-b210553f9814");
                    conn.setRequestProperty("accesstoken", "5ff94f871ab7d083bbe2f77c62f59ded547bf1f30196361f7ea41a6d340afa3f");
                }
                ;
            } else {
                conn.setRequestProperty("uuid", "31a1a17f-4007-42bd-b07f-c1cd1c64da8b");
            }
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "okhttp/3.6.0");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("appv", "17");
            conn.setRequestProperty("network", "WIFI");
            conn.setRequestProperty("osn", "ANDROID");
            conn.setRequestProperty("osv", "4.3.1");

            conn.setRequestProperty("Host", "api.jushiyaoye.com");

            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     */
    public static String sendPaopaoPost(String url, String param, String user) {
        if (user == null) {
            Cookie = Cookie1;
        } else {
            if (user.contains("1766")) {
                Cookie = Cookie2;
            } else {
                Cookie = Cookie1;
            }
        }
        System.out.println(url + param);
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) realUrl.openConnection();
            // 打开和URL之间的连接

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法


            // 设置通用的请求属性

            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");

            conn.setRequestProperty("Cookie", Cookie);
            System.out.println(Cookie);
            conn.setRequestProperty("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.4; HM NOTE 1S MIUI/5.7.16)");
            conn.setRequestProperty("Host", "www.8892236.cn:1340");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Length", "112");


            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (url.contains("apilogin")) {
                if (user.contains("1766")) {
                    Cookie2 = conn.getHeaderField("set-cookie");
                } else {
                    Cookie1 = conn.getHeaderField("set-cookie");
                }


            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
