package com.ruixin.util;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class SpeechClient {
    private static volatile SpeechClient speechClient;
    private AipSpeech client = null;
    public static String gender=null;
    public static String speed=null;

    private SpeechClient(String APP_ID, String API_KEY, String SECRET_KEY) {
        client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
    }

    public static SpeechClient getInstance(String APP_ID, String API_KEY, String SECRET_KEY) {
        if (speechClient == null) {
            synchronized (SpeechClient.class) {
                if (speechClient == null) {
                    speechClient = new SpeechClient(APP_ID, API_KEY, SECRET_KEY);
                }
            }
        }
        return speechClient;
    }

    /**
     * @param
     * @return
     */
    public String generateMp3(String text, String path) {
        String name = null;
        String fileName = null;
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        if(gender.equals("男")) {
            options.put("per", "1");
        }else{
            options.put("per",0);
        }
        if(speed.equals("慢")){
            options.put("spd",1);
        }else if(speed.equals("快")){
            options.put("spd",9);
        }else{
            options.put("spd",5);
        }
        TtsResponse res = client.synthesis(text, "zh", 1, options);
        byte[] data = res.getData();
        //定义变量调用
        //
        // 转换格式
        if (data != null) {
            try {
                name = UUID.randomUUID().toString() + ".mp3";
                fileName = path + "/" + name;
                Util.writeBytesToFileSystem(data, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
