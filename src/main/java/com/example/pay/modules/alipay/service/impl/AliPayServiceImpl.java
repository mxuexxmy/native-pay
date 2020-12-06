package com.example.pay.modules.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.pay.common.util.OrderNumberUtils;
import com.example.pay.modules.alipay.service.IAliPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author mxuexxmy
 * @ClassName AliPayController
 * @Description TODO
 * @Date 11/23/2020 8:34 PM
 * @Version 1.0
 **/
@Service
public class AliPayServiceImpl implements IAliPayService {

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

    AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset, alipay_public_key, sign_type);

    public AliPayServiceImpl() throws AlipayApiException {
    }

    @Override
    public String testPcPay() {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl("");
        request.setReturnUrl("");
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setBody("测试");
        model.setSubject("测试");
        model.setQrPayMode("4");
        model.setTotalAmount("13.14");
        model.setOutTradeNo(OrderNumberUtils.getOutTradeNo());
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    @Override
    public String qrCodePcPay() {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest (); //创建API对应的request类
        request.setNotifyUrl("111");
        request.setReturnUrl("111");
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setBody("测试");
        model.setSubject("测试二维码支付");
        model.setTotalAmount("11.11");
        model.setOutTradeNo(OrderNumberUtils.getOutTradeNo());
        model.setTimeoutExpress("15m");
        request.setBizModel(model);
        String codeUrl = "";
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
               return response.getQrCode();
            }
            return "失败";
        } catch (AlipayApiException e) {
            e.printStackTrace();

        }
        return codeUrl;
    }
}
