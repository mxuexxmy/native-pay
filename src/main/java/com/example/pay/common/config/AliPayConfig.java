package com.example.pay.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mxuexxmy
 * @ClassName AliPayConfig
 * @Description TODO
 * @Date 11/16/2020 11:39 PM
 * @Version 1.0
 **/
@Component
public class AliPayConfig {

    /** 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 **/
    @Value("${application.alipay.app_id}")
    private String app_id;

    /** 商户私钥，您的PKCS8格式RSA2私钥 **/
    @Value("application.alipay.merchant_private_key")
    private String merchant_private_key;

    /** 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。 **/
    @Value("application.alipay.alipay_public_key")
    private String alipay_public_key;

    /** 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 **/
    @Value("application.alipay.notify_url")
    private String notify_url;

    /** 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 **/
    @Value("application.alipay.return_url")
    private String return_url;

    /** 签名方式 **/
    @Value("application.alipay.sign_type")
    private String sign_type;

    /** 字符编码格式 **/
    @Value("application.alipay.charset")
    private String charset;

    @Value("application.alipay.format")
    private String format;

    /** 支付宝网关 **/
    @Value("application.alipay.gatewayUrl")
    private String gatewayUrl;
}
