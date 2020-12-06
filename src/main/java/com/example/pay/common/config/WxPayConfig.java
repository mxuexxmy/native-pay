package com.example.pay.common.config;

import com.example.pay.modules.wxpay.util.IWXPayDomain;
import com.example.pay.modules.wxpay.util.WXPayConfig;
import com.example.pay.modules.wxpay.util.WXPayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * @author mxuexxmy
 * @date 12/5/2020$ 2:57 PM$
 */
@Component
public class WxPayConfig extends WXPayConfig{

    @Value("${application.wxpay.app-id}")
    private String appId;

    @Value("${application.wxpay.mch-id}")
    private String mchId;

    @Value("${application.wxpay.key}")
    private String key;

    @Value("${application.wxpay.cert-path}")
    private String certPath;

    private byte[] certData;

    @PostConstruct
    public void init() throws Exception{
        File file=new File(certPath);
        FileInputStream in=new FileInputStream(file);
        BufferedInputStream bin=new BufferedInputStream(in);
        this.certData=new byte[(int)file.length()];
        bin.read(this.certData);
        bin.close();
        in.close();
    }

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream in=new ByteArrayInputStream(this.certData);
        return in;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API,true);
            }
        };
    }
}
