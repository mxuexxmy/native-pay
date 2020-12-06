package com.example.pay.modules.alipay.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.example.pay.modules.alipay.service.IAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author mxuexxmy
 * @ClassName AliPayController
 * @Description TODO
 * @Date 11/23/2020 8:43 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("ali-pay")
public class AliPayController {

    @Resource
    private IAliPayService aliPayService;

    @GetMapping
    public void testPcPAy(HttpServletResponse response) {
        try {
            String form = aliPayService.testPcPay();
            response.setContentType("text/html;charset="  + "utf-8");
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("qc-pc-pay")
    public String qrCodePcPay() {
      return aliPayService.qrCodePcPay();
    }


}
