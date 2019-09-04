package com.jf11.config.parse;

import com.jf11.Bean;
import com.jf11.Property;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JF11
 * @description: 解析 XML 文件的配置管理器，读取配置文件
 * <p>
 * Created by jalr on 2019/8/29.
 */
public class ConfigManager {

    public static Map<String, Bean> getConfig(String path) {
        SAXReader reader = new SAXReader();
        Map<String, Bean> map = new HashMap<String, Bean>();
        Document doc;
        try {
            doc = reader.read(ConfigManager.class.getResourceAsStream(path));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("ConfigManager parse path error, try another path.");
        }
        String xpath = "//bean"; //查找配置文件下的所有 bean
        List<Element> list = doc.selectNodes(xpath);

        if (list != null) {
            for (Element elm : list) { //每一个 element 是一个 bean
                Bean bean = new Bean();
                String name = elm.attributeValue("name");
                String className = elm.attributeValue("class");
                bean.setName(name);
                bean.setClassName(className);

                List<Element> child = elm.elements("property"); //bean 的 property
                if (child != null) {
                    for (Element childElm : child) {
                        Property property = new Property();
                        String pName = childElm.attributeValue("name");
                        String pVal = childElm.attributeValue("value");
                        String pRef = childElm.attributeValue("ref");
                        property.setName(pName);
                        property.setValue(pVal);
                        property.setRef(pRef);
                        bean.getProperties().add(property);//封装 property 到 bean
                    }
                }
                map.put(name, bean); //初始化的 bean 封装到 map
            }
        }
        return map;
    }
}
