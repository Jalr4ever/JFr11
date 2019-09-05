package com.jf11;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JF11
 * @description: 容器中的 Bean 映射
 * <p>
 * Created by jalr on 2019/8/29.
 */
public class Bean {
    private String name;
    private String className;
    private String scope = "singleton";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    private List<Property> properties = new ArrayList<Property>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", scope='" + scope + '\'' +
                ", properties=" + properties +
                '}';
    }
}
