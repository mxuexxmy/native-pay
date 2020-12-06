package com.example.pay.modules.wxpay.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.example.pay.common.config.WxPayConfig;
import com.example.pay.common.util.OrderNumberUtils;
import com.example.pay.modules.wxpay.util.WXPay;
import com.example.pay.modules.wxpay.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mxuexxmy
 * @ClassName WxPayController
 * @Description TODO
 * @Date 11/23/2020 10:04 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("wx-pay")
public class WxPayController {

    @Value("${application.wxpay.app-id}")
    private String appId;

    @Value("${application.wxpay.app-secret}")
    private String appSecret;

    @Value("${application.wxpay.key}")
    private String key;

    @Value("${application.wxpay.mch-id}")
    private String mchId;

    @Resource
    private WxPayConfig wxPayConfig;

    @GetMapping("pc-pay")
    public String pcPay() {
        try {
            WXPay wxPay = new WXPay(wxPayConfig);
            HashMap map = new HashMap();
            map.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串
            map.put("body", "订单备注");
            map.put("out_trade_no", OrderNumberUtils.getOutTradeNo());
            map.put("total_fee", "1");
            map.put("spbill_create_ip", "127.0.0.1");
            map.put("notify_url", "https://127.0.0.1/test");
            map.put("trade_type", "NATIVE");
            String sign=WXPayUtil.generateSignature(map,key);
            map.put("sign",sign);
            Map<String, String> result = wxPay.unifiedOrder(map);
            String prepayId = result.get("prepay_id");
            String codeUrl=result.get("code_url");
            if (prepayId != null) {
                return codeUrl;
            } else {
                return "支付订单创建失败";
            }
        } catch (Exception e) {

        }
        return null;
    }




}
