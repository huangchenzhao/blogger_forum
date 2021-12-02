package com.ruixin.controller;

import com.ruixin.bean.User;
import com.ruixin.common.utils.Views;
import com.ruixin.service.UserService;
import com.ruixin.util.FaceClient;
import com.ruixin.util.SpeechClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/face")
public class FaceController {
    // https://login.bce.baidu.com/
    private static String APP_ID = "24589475";
    private static String API_KEY = "bDMsSh3KFR3qpXv12LSbWKbv";
    private static String SECRET_KEY = "nzV9bcdiZAha32WYwjmlS3UptOtAXuPO";
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String faceRegister() {
        return Views.FACE_REGISTER;
    }

    @RequestMapping("/facedetect")
    public void faceDetect(@RequestParam("username") String username, @RequestParam("data") String data,HttpServletResponse res) throws Exception {
        FaceClient faceClient = FaceClient.getInstance(APP_ID, API_KEY, SECRET_KEY);
        JSONObject json = faceClient.faceFeatureDetect(data);

        //       String data2=faceClient.faceReader("admin1");
        //       boolean match=faceClient.faceContrast(data2,data);
        if (null != json) {
            faceClient.faceSaver(username, data);
            userService.faceRegister(username, username + ".txt");
            res.getWriter().print(1);
        } else {
            res.getWriter().print(2);
        }
    }

    @RequestMapping("/login")
    public void faceLogin(@RequestParam("username") String username, @RequestParam("data") String data, HttpServletResponse res) throws Exception {
        FaceClient faceClient = FaceClient.getInstance(APP_ID, API_KEY, SECRET_KEY);
        JSONObject json = faceClient.faceFeatureDetect(data);
        String userFace = userService.findFace(username);
        if (null != json && null != userFace) {
            String face = faceClient.faceReader(userFace);
            if (faceClient.faceContrast(face, data)) {
                String password=userService.findPassword(username);
                AuthenticationToken token = new UsernamePasswordToken(username, password, true);
                ((UsernamePasswordToken) token).setRememberMe(true);
                SecurityUtils.getSubject().login(token);
                res.getWriter().print(1);
            }
        }
        res.getWriter().print(2);
    }
}