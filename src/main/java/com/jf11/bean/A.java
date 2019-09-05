package com.jf11.bean;

import java.util.List;

/**
 * @program: JF11
 * @description: 测试类 A
 * <p>
 * Created by jalr on 2019/8/30.
 */
public class A {
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    private int name;

    public A() {
        System.out.println("A created!");
    }


}