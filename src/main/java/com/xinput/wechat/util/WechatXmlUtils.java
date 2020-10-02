package com.xinput.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.xinput.bleach.util.Logs;
import com.xinput.bleach.util.StringUtils;
import com.xinput.wechat.exception.WechatPayException;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-22 15:12
 */
public class WechatXmlUtils {

    private static final Logger logger = Logs.get();

    private static XStream xstream;

    static {
        xstream = new XStream();
        xstream.registerConverter(new MapEntryConverter());
        xstream.alias("xml", Map.class);
    }

    /**
     * 将bean通过保存的xml字符串转换成map.
     */
    public static Map<String, Object> toMap(String xmlString) throws WechatPayException {
        if (StringUtils.isNullOrEmpty(xmlString)) {
            new HashMap<>();
        }
        try {

            Map<String, Object> result = new HashMap<>();
            // 将xml字符串转换成Document对象，以便读取其元素值
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));

            NodeList list = (NodeList) XPathFactory.newInstance().newXPath()
                    .compile("/xml/*")
                    .evaluate(doc, XPathConstants.NODESET);
            int len = list.getLength();
            for (int i = 0; i < len; i++) {
                result.put(list.item(i).getNodeName(), list.item(i).getTextContent());
            }

            return result;
        } catch (Exception e) {
            logger.error("xml数据转Map类型出错.xml:[{}].", xmlString, e);
            throw new WechatPayException("xml数据转Map类型出错.", e);
        }
    }
}
