package com.xinput.wechat.util;

import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.XmlUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechat.config.WechatConfig;
import com.xinput.wechat.enums.SignTypeEnum;
import com.xinput.wechat.exception.WechatException;
import com.xinput.wechat.request.BaseWeChatPayRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 15:41
 */
public final class WechatHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(WechatHttpUtils.class);

    public static String withoutCertQequest(String url, BaseWeChatPayRequest baseReq) throws Exception {
        return post(url, baseReq, false);
    }

    /**
     * post请求
     *
     * @param url     接口URL
     * @param baseReq 接口请求参数
     * @return
     */
    public static String withCertPost(String url, BaseWeChatPayRequest baseReq) throws Exception {
        return post(url, baseReq, true);
    }

    public static String post(String url, BaseWeChatPayRequest baseReq, boolean useCert) throws Exception {
        // 设置有效的验证方式
        SignTypeEnum signTypeEnum = SignTypeEnum.getAllowSign();
        baseReq.setSign_type(signTypeEnum.getType());
        logger.info("request url:[{}]. params:[{}]", url, JsonUtils.toJsonString(baseReq));
        baseReq.setAppid(WechatConfig.getWechatAppid());
        baseReq.setMch_id(WechatConfig.getWechatMchId());
        if (StringUtils.isNullOrEmpty(baseReq.getNonce_str())) {
            baseReq.setNonce_str(WechatPayUtils.createNonce());
        }

        // MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        baseReq.setSign(WechatPayUtils.generateSignature(BeanMapUtils.toMap(baseReq), signTypeEnum));

        return execute(url, XmlUtils.toXml(baseReq), useCert);
    }

    public static String execute(String url, String data, boolean useCert) throws Exception {
        String certPath = WechatConfig.getWechatApiCertPath();
        String certPass = WechatConfig.getWechatMchId();
        if (useCert) {
            if (StringUtils.isNullOrEmpty(certPath)) {
                logger.error("[wechat.api.cert.path] not found.");
                throw new WechatException("wechat.api.cert.path not found.");
            }

            if (StringUtils.isNullOrEmpty(certPass)) {
                logger.error("[wechat.mch.id] not found.");
                throw new WechatException("wechat.api.cert.path not found.");
            }
        }
        return execute(url, data, useCert, certPath, certPass);
    }

    public static String execute(String url, String data, boolean useCert, String certPath, String certPass) throws Exception {
        return execute(url, data, useCert, certPath, certPass, null);
    }

    /**
     * post 请求
     *
     * @param url      请求url
     * @param data     请求参数
     * @param certPath 证书路径
     * @param certPass 证书密码,就是对应的商户号
     * @return {@link String} 请求返回的结果
     */
    private static String execute(String url, String data, boolean useCert, String certPath, String certPass, InputStream certFile) throws Exception {
        BasicHttpClientConnectionManager connManager = createConnManager(useCert, certPath, certPass, certFile);
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000 * WechatConfig.getWechatTimeout())
                .setConnectTimeout(1000 * WechatConfig.getWechatTimeout())
                .build();
        httpPost.setConfig(requestConfig);

        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(data, "UTF-8"));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        logger.info("request url:[{}], response code: [{}].", url, httpResponse.getStatusLine().getStatusCode());
        HttpEntity httpEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(httpEntity, "UTF-8");
        if (WechatConfig.getUseSandbox()) {
            logger.info("request url:[{}],\n request data : [{}],\n response result:[{}].", url, data, result);
        }
        return result;
    }

    private static final BasicHttpClientConnectionManager createConnManager(boolean useCert, String certPath, String certPass, InputStream certFile) throws Exception {
        BasicHttpClientConnectionManager connManager;
        try {
            if (useCert) {
                char[] password = WechatConfig.getWechatMchId().toCharArray();
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                if (certFile != null) {
                    keyStore.load(certFile, certPass.toCharArray());
                } else {
                    keyStore.load(new FileInputStream(certPath), certPass.toCharArray());
                }
                // 实例化密钥库 & 初始化密钥工厂
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(keyStore, password);

                // 创建 SSLContext
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                        sslContext,
                        new String[]{"TLSv1"},
                        null,
                        new DefaultHostnameVerifier());

                connManager = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", sslConnectionSocketFactory)
                                .build(),
                        null,
                        null,
                        null
                );
            } else {
                connManager = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                                .build(),
                        null,
                        null,
                        null
                );
            }
        } catch (Exception e) {
            logger.error("create BasicHttpClientConnectionManager exception.", e);
            throw e;
        }

        return connManager;
    }
}
