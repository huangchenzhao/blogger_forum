package com.ruixin.controller;

import com.ruixin.bean.Comment;
import com.ruixin.service.CommentService;
import com.ruixin.util.SpeechClient;
import javazoom.jl.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class CommentController {

    private static String APP_ID = "24599155";
    private static String API_KEY = "V6g20ex7LjN45E7KWZpeHqP3";
    private static String SECRET_KEY = "u8iqC9UudjOrrNokqvwKYAYIhxkUqluj";

    public static Player player = null;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/add")
    @ResponseBody
    public String CommentAdd(@RequestParam("container")String container,@RequestParam("newsid") String newsId){
        Comment comment = new Comment();
        comment.setContent(container);
        comment.setNewsId(Integer.parseInt(newsId));
        comment.setStatus("0");

        String text=comment.getContent();
        System.out.println(text);
        SpeechClient speechClient = SpeechClient.getInstance(APP_ID, API_KEY, SECRET_KEY);
        String file = speechClient.generateMp3(text, "E:/IdeaProjects/hblog2/src/main/webapp/static/temp/audio");

        try {
            File mp3 = new File(file);
            FileInputStream fileInputStream = new FileInputStream(mp3);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new Player(bufferedInputStream);
            player.play();

        }catch (Exception e){
            e.printStackTrace();
        }

        commentService.save(comment);
        return "success";
    }
}
