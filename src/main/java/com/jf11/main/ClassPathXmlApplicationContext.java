package com.jf11.main;

import com.jf11.Bean;
import com.jf11.Property;
import com.jf11.config.parse.ConfigManager;
import com.jf11.utils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JF11
 * @description: Bean 工厂实现类，上下文对象
 * <p>
 * Created by jalr on 2019/9/4.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Bean> config;

    private Map<String, Object> context = new HashMap<String, Object>();

    public Object getBean(String beanName) {
        Object bean = context.get(beanName);
        if (bean == null) {
            bean = createBean(config.get(beanName));
        }
        return bean;
    }

    public ClassPathXmlApplicationContext(String path) {
        config = ConfigManager.getConfig(path);
        if (config != null) {
            for (Map.Entry<String, Bean> entry : config.entrySet()) {
                String beanName = entry.getKey();
                Bean bean = entry.getValue();
                Object existBean = context.get(beanName);
                if (existBean == null && bean.getScope().equals("singleton")) {
                    Object beanObj = createBean(bean);
                    context.put(beanName, beanObj);
                }
            }
        }
    }

    private Object createBean(Bean bean) {
        String className = bean.getClassName();
        Class beanClass;
        try {
            beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong class path, please check in applicationContext.xml.");
        }

        Object beanObj;

        /***注入 bean 对象相关的值或 bean***/
        try {
            beanObj = beanClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong constructor, bean has not empty constructor.");
        }

        if (bean.getProperties() != null) {
            for (Property property : bean.getProperties()) {
                String name = property.getName();
                String value = property.getValue();
                String ref = property.getRef();

                Method setMethod = BeanUtils.getWriteMethod(beanObj, name);
                Object injectParam = null;

                if (value != null) { //有值需要注入
                    Map<String, String[]> paramMap = new HashMap<String, String[]>();
                    paramMap.put(name, new String[]{value});
                    try {
                        org.apache.commons.beanutils.BeanUtils.populate(beanObj, paramMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Please check property " + name + " is corrected.");
                    }
                }

                if (property.getRef() != null) { //获取要注入的 bean
                    Object existBean = context.get(property.getRef());
                    if (existBean == null) { //容器不存在 bean
                        existBean = createBean(config.get(property.getRef())); //创建 bean 的 property 指定的 bean
                        if (config.get(property.getRef()).getScope().equals("singleton"))
                            context.put(property.getRef(), existBean); //注入引入名称对应的 bean
                    }
                    injectParam = existBean;
                    try {
                        if (setMethod != null) {
                            setMethod.invoke(beanObj, injectParam); //注入
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Bean property - " + name + " has no correct set method or wrong parameter.");
                    }
                }

            }
        }
        return beanObj;
    }
}
