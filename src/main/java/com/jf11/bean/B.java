package com.jf11.bean;

/**
 * @program: JF11
 * @description: 测试类 B
 * <p>
 * Created by jalr on 2019/9/4.
 */
public class B {
    private A a;

    public B() {
        System.out.println("B created!");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
