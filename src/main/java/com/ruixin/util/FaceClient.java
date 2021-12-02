package com.ruixin.util;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class FaceClient {
    private static volatile FaceClient faceClient;
    private AipFace client = null;

    private FaceClient(String APP_ID, String API_KEY, String SECRET_KEY) {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    public static FaceClient getInstance(String APP_ID, String API_KEY, String SECRET_KEY) {
        if (faceClient == null) {
            synchronized (FaceClient.class) {
                if (faceClient == null) {
                    faceClient = new FaceClient(APP_ID, API_KEY, SECRET_KEY);
                }
            }
        }
        return faceClient;
    }

    /**
     * @param人脸检测
     * @return
     */
    public boolean faceDetect(String image) {
        // options是检查项参数
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "1"); // max_face_num检测的时候摄像头只能有一张人脸
        // glassess,age
        // 检测人脸，调用AipFace类汇总的detect方法，来检测人脸
        JSONObject result = client.detect(image, "BASE64", options);

        System.out.println(result.toString());

        // 类似hashmap  {key:value,key:[{key2:value2}]} //

        Object resultObj = result.get("result"); //{"result":null,}
        // 没有检测到人脸
        if (resultObj == null || resultObj.toString().equals("null")) {
            return false;
        }
        // 检测到人脸了{"result":{"face_num":1, },}
        JSONObject faceObj = (JSONObject) resultObj;
        if (faceObj.getInt("face_num") == 1) {
            return true;
        }
        return false;
    }
    /**
     * 人脸特征
     */
    public JSONObject faceFeatureDetect(String image) {
        // options是检查项参数
        HashMap<String, String> options = new HashMap<String, String>();
        // age: 年龄 beauty: 颜值 expression：表情 faceshape：脸型  glasses：眼镜
        options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,race,qualities");
        options.put("max_face_num", "1");
        options.put("face_type", "LIVE");

        JSONObject result = client.detect(image, "BASE64", options);

        System.out.println(result.toString());

        // 类似hashmap  {key:value,key:[{key2:value2}]} //

        Object resultObj = result.get("result"); //{"result":null,}
        // 没有检测到人脸
        if (resultObj == null || resultObj.toString().equals("null")) {
            return null;
        }
        // 检测到人脸了{"result":{"face_num":1, },}
        JSONObject faceObj = (JSONObject) resultObj;
        if (faceObj.getInt("face_num") >= 1) {
            return faceObj;
        }
        return null;
    }

    /**
     * 人脸比对
     * @param image1
     * @param image2
     * @return
     */
    public boolean faceContrast(String image1, String image2) {

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        // 可以检测图片中是否有人脸
        // client.detect(image, imageType, options)

        JSONObject res = client.match(requests); //
        System.out.println(res.toString());
        Object object = res.get("result");
        System.out.println("cccc==" + object);
        if (object == null || object.toString().equals("null")) {
            return false;
        } else {
            res = (JSONObject) object;
            double result = res.getDouble("score"); // score里存放了比对结果
            if (result >= 80) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 存储人脸
     * @param userName
     * @param data
     * @throws Exception
     */
    public void faceSaver(String userName,String data) throws Exception{
        String fileName=userName+".txt";
        File f=new File("E:\\IdeaProjects\\hblog2\\src\\main\\webapp\\static\\face"+fileName);
        f.createNewFile();
        PrintWriter out=new PrintWriter(new FileWriter("E:\\IdeaProjects\\hblog2\\src\\main\\webapp\\static\\face"+fileName));
        out.println(data);
        out.close();
    }

    /**
     * 读取人脸
     * @param userName
     * @return
     */
    public String faceReader(String userName){
        String ss = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader("E:\\IdeaProjects\\hblog2\\src\\main\\webapp\\static\\face"+userName));
            String s = in.readLine();
            while (s!= null) {
                ss += s;
                s = in.readLine();
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ss;
    }
}
