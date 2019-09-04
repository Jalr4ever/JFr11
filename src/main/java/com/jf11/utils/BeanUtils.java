package com.jf11.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @program: JF11
 * @description: Bean 工具类
 * <p>
 * Created by jalr on 2019/9/4.
 */
public class BeanUtils {
    public static Method getWriteMethod(Object beanObj, String propertyName) {
        BeanInfo info;
        Method writeMethod = null;
        try {
            info = Introspector.getBeanInfo(beanObj.getClass());
        } catch (IntrospectionException e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong introspector, class is not a bean");
        }
        PropertyDescriptor[] pdcpt = info.getPropertyDescriptors();//所有属性的描述器
        if (pdcpt != null) {
            for (PropertyDescriptor pd : pdcpt) {
                String pName = pd.getName();
                if (pName.equals(propertyName)) {
                    writeMethod = pd.getWriteMethod();
                }
            }
        }

        if (writeMethod == null) {
            throw new RuntimeException("Wrong set method, please check if " + propertyName + " set method is create.");
        }

        return writeMethod;
    }
}
