package com.jf11.bean;

/**
 * @program: JF11
 * @description: 测试用例 C
 * <p>
 * Created by jalr on 2019/9/5.
 */
public class C {
    private B b;

    public C() {
        System.out.println("C created!");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
