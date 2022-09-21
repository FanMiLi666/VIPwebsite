package com.example.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.google.zxing.qrcode.encoder.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class VIPController {

    //返回了一个hello
    @RequestMapping("/hello")
    public String test(Model model) {
        //填充
        model.addAttribute("user", "范世伟");
        return "java";
    }

    //返回了一个hello
    @RequestMapping("/toPlay")
    public String toPlay() {
        return "play";
    }

    //返回了一个hello
    @RequestMapping("/doPlay")
    public String doPlay(String url, boolean isQR , HttpServletResponse response) throws IOException {
        if (isQR) { //勾选 -> 扫码
            //1.准备一个播放的地址
            String playUrl = "https://jx.bozrc.com:4433/player/?url="+url;
            //2.创建要的图片
            BufferedImage image = QrCodeUtil.generate(playUrl, 400, 400);
            //3.发送给客户端(Response ->
            ImageIO.write(image,"png",response.getOutputStream());

        }else {
            return "redirect:https://jx.bozrc.com:4433/player/?url="+url;
        }
        return null;
    }
}
