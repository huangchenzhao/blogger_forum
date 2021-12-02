package com.ruixin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
public class UploadController {

    @PostMapping("/file/upload")
    @ResponseBody
    public JSONObject uploadImg(@RequestParam("file") MultipartFile file,
                                HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("upload/");
        String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
        String reName=date+"/"+UUID.randomUUID()+".jpg";
        String str="";
        try {
            File targetFile = new File(filePath+date);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+reName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            str=String.format("{code:0,msg:'success',data:{src:'/upload/%s'}}",reName);
        } catch (Exception e) {
            str="{msg:'接口调用异常'}";
            e.printStackTrace();
        }
        return JSON.parseObject(str);
    }
}
