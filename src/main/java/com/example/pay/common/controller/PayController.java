package com.example.pay.common.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author mxuexxmy
 * @date 12/5/2020$ 3:42 PM$
 */
@RestController
@RequestMapping("pay")
public class PayController {

    @GetMapping("/qrcode")
    public void qrcode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String codeUrl= request.getParameter("codeUrl");
        if(codeUrl!=null&&codeUrl.length()>0){
            QrConfig qrConfig=new QrConfig();
            qrConfig.setWidth(250);
            qrConfig.setHeight(250);
            qrConfig.setMargin(2);
            OutputStream out=response.getOutputStream();
            QrCodeUtil.generate(codeUrl,qrConfig,"jpg",out);
            out.close();
        }
    }

}
