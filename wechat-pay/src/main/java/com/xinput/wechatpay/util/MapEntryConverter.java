package com.xinput.wechatpay.util;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-22 15:03
 */
public class MapEntryConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        AbstractMap map = (AbstractMap) o;
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            hierarchicalStreamWriter.startNode(entry.getKey().toString());
            Object val = entry.getValue();
            if (null != val) {
                hierarchicalStreamWriter.setValue(val.toString());
            }
            hierarchicalStreamWriter.endNode();
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Map<String, String> map = new HashMap<>();

        while (hierarchicalStreamReader.hasMoreChildren()) {
            hierarchicalStreamReader.moveDown();

            // nodeName aka element's name
            String key = hierarchicalStreamReader.getNodeName();
            String value = hierarchicalStreamReader.getValue();
            map.put(key, value);

            hierarchicalStreamReader.moveUp();
        }

        return map;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return AbstractMap.class.isAssignableFrom(aClass);
    }
}
