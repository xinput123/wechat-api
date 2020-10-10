package com.xinput.wechatpay.util;

import com.xinput.bleach.util.JsonUtils;
import com.xinput.bleach.util.StringUtils;
import com.xinput.bleach.util.XmlUtils;
import com.xinput.bleach.util.bean.BeanMapUtils;
import com.xinput.wechatpay.config.WechatConfig;
import com.xinput.wechatpay.enums.SignTypeEnum;
import com.xinput.wechatpay.exception.WechatPayException;
import com.xinput.wechatpay.request.BaseWeChatPayRequest;
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
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-18 15:41
 */
public final class WechatHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(WechatHttpUtils.class);

    private static BasicHttpClientConnectionManager CONN_MANAGER;

    private static BasicHttpClientConnectionManager USE_CERT_CONNMANAGER;

    public static String withoutCertQequest(String url, BaseWeChatPayRequest baseReq) throws WechatPayException {
        return post(url, baseReq, false);
    }

    /**
     * post请求
     *
     * @param url     接口URL
     * @param baseReq 接口请求参数
     * @return
     */
    public static String withCertPost(String url, BaseWeChatPayRequest baseReq) throws WechatPayException {
        return post(url, baseReq, true);
    }

    public static String post(String url, BaseWeChatPayRequest baseReq, boolean useCert) throws WechatPayException {
        // 设置有效的验证方式
        SignTypeEnum signTypeEnum = SignTypeEnum.getAllowSign();
        baseReq.setSign_type(signTypeEnum.getType());
        logger.info("request url:[{}]. params:[{}]", url, JsonUtils.toJsonString(baseReq));
        baseReq.setAppid(WechatConfig.getWechatAppid());
        baseReq.setMch_id(WechatConfig.getWechatMchId());
        if (StringUtils.isNullOrEmpty(baseReq.getNonce_str())) {
            baseReq.setNonce_str(WechatPayUtils.createNonce());
        }

        // 生成签名，这里的签名是用于验证当前请求是否有效
        baseReq.setSign(WechatPayUtils.generateSignature(BeanMapUtils.toMap(baseReq), signTypeEnum));

        // 参数验证
        baseReq.checkConstraints();
        return execute(url, XmlUtils.toXml(baseReq), useCert);
    }

    /**
     * post 请求
     *
     * @param url  请求url
     * @param data 请求参数
     * @return {@link String} 请求返回的结果
     */
    public static String execute(String url, String data, boolean useCert) throws WechatPayException {
        BasicHttpClientConnectionManager connManager = getConnManager(useCert);
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
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            logger.info("request url:[{}], response code: [{}].", url, httpResponse.getStatusLine().getStatusCode());
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            if (WechatConfig.getEnableWechatPayLog()) {
                logger.info("request url:[{}],\n request data : [{}],\n response result:[{}].", url, data, result);
            }
            return result;
        } catch (Exception e) {
            logger.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, data, e.getMessage());
            throw new WechatPayException(e.getMessage(), e);
        }
    }

    private static final BasicHttpClientConnectionManager getConnManager(boolean useCert) throws WechatPayException {
        if (useCert) {
            return getUseCertConnmanager();
        } else {
            return getConnManager();
        }
    }

    private static final BasicHttpClientConnectionManager getConnManager() {
        if (CONN_MANAGER == null) {
            CONN_MANAGER = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    null
            );
        }
        return CONN_MANAGER;
    }

    private static final BasicHttpClientConnectionManager getUseCertConnmanager() throws WechatPayException {
        String certPath = WechatConfig.getWechatApiCertPath();

        // 商户号,也是证书密码
        String mchId = WechatConfig.getWechatMchId();

        if (StringUtils.isNullOrEmpty(certPath)) {
            throw new WechatPayException("请确保证书文件地址keyPath已配置");
        }

        if (StringUtils.isNullOrEmpty(mchId)) {
            throw new WechatPayException("请确保商户号mchId已设置");
        }

        if (USE_CERT_CONNMANAGER == null) {
            char[] password = WechatConfig.getWechatMchId().toCharArray();
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(new FileInputStream(certPath), mchId.toCharArray());

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

                USE_CERT_CONNMANAGER = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", sslConnectionSocketFactory)
                                .build(),
                        null,
                        null,
                        null
                );
            } catch (Exception e) {
                logger.error("create BasicHttpClientConnectionManager exception.", e);
                throw new WechatPayException("证书文件有问题，请核实！", e);
            }
        }
        return USE_CERT_CONNMANAGER;
    }

}
