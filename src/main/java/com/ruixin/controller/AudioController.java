package com.ruixin.controller;

import com.database.data.Test;
import com.ruixin.common.utils.Views;
import com.ruixin.util.SpeechClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-27 10:57
 * @Version 1.0.0
 */
@Controller
public class AudioController {
    @RequestMapping("/audio")
    public String audio(Test test){
        SpeechClient.gender=test.getGender();
        SpeechClient.speed=test.getSpeed();
        return Views.AUDIO;
    }
}
