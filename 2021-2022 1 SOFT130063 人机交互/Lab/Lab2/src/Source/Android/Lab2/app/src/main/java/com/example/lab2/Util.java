package com.example.lab2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import okhttp3.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.FutureTask;

public class Util {
    public static String postInit(String url, int groundWidth, int groundHeight, float currX, float currY) {
        return Util.sendPost(url + "postInit", "{\"groundWidth\":" + groundWidth + ",\"groundHeight\":"
                + groundHeight + ",\"currX\":" + currX + ",\"currY\":" + currY + "}");
    }

    public static String getCurrPos(String url) {
        return Util.sendGet(url + "getCurrPos", "");
    }

    public static String postCurrPos(String url, float currX, float currY) {
        return Util.sendPost(url + "postCurrPos", "{\"currX\":" + currX + ",\"currY\":" + currY + "}");
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url  发送请求的URL
     * @param param   请求参数，格式：name1=value1&name2=value2
     * @return String 响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString;
            if (param.equals("")) {
                urlNameString = url;
            } else {
                urlNameString = url + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/json");
            // 建立实际的连接
            connection.connect();
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
     * @param url 发送请求的 URL
     * @param param 请求参数，格式： name1=value1&name2=value2
     * @return  响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！"+e);
            e.printStackTrace();
        }

        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static boolean uploadFile(String path, String filename) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(path);
        if (path.isEmpty() || !file.exists()) {
            return false;
        }

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, RequestBody.create(new File(path), MediaType.parse("multipart/form-data")))
                .addFormDataPart("fileName", filename)
                .build();

        FutureTask<Boolean> task = new FutureTask<>(()->
        {
            try {
                ResponseBody responseBody = okHttpClient.newCall(
                        new Request.Builder().post(body).url("http://1.15.221.182:8081/upload").build()
                ).execute().body();

                if (responseBody != null) {
                    return Boolean.parseBoolean(responseBody.string());
                }

                return false;
            } catch (IOException e) {
                return false;
            }
        });

        try {
            new Thread(task).start();
            return task.get();
        } catch (Exception e) {
            return false;
        }
    }

    public static Bitmap downloadFile() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fileName", "temp.jpg")
                .build();

        FutureTask<Bitmap> task = new FutureTask<>(()->
        {
            ResponseBody responseBody = okHttpClient.newCall(
                    new Request.Builder().post(body).url("http://1.15.221.182:8081/download").build()
            ).execute().body();

            if (responseBody != null) {
                try (InputStream inputStream = responseBody.byteStream()) {
                    return BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    return null;
                }
            }
            return null;
        });

        try {
            new Thread(task).start();
            return task.get();
        } catch (Exception e) {
            return null;
        }
    }
}
