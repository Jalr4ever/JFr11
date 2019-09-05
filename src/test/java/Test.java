import com.jf11.Bean;
import com.jf11.config.parse.ConfigManager;
import com.jf11.main.BeanFactory;
import com.jf11.main.ClassPathXmlApplicationContext;
import com.jf11.bean.*;
import java.util.Map;

/**
 * @program: JF11
 * @description: 框架构建期间的测试
 * <p>
 * Created by jalr on 2019/8/30.
 */
public class Test {

    //测试 ConfigManager
    @org.junit.Test
    public void testI(){
        Map<String, Bean> config = ConfigManager.getConfig("/applicationContext.xml");
        System.out.println(config);
    }

    //测试 bean 对象是否正常
    @org.junit.Test
    public void testII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        C c = (C) bf.getBean("C");
        System.out.println(c.getB().getA().getName());
    }

    //singleton 测试
    @org.junit.Test
    public void testIII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        A a1 = (A) bf.getBean("A");
        A a2 = (A) bf.getBean("A");
        A a3 = (A) bf.getBean("A");

    }

    //prototype 测试1
    @org.junit.Test
    public void testIIII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        B b1 = (B) bf.getBean("B");
        B b2 = (B) bf.getBean("B");
        B b3 = (B) bf.getBean("B");
    }

    //prototype 测试2
    @org.junit.Test
    public void testIIIII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        C c1 = (C) bf.getBean("C");
        C c2 = (C) bf.getBean("C");
        C c3 = (C) bf.getBean("C");
    }

    //测试 A 对象的值类型自动转换注入
    @org.junit.Test
    public void testIIIIII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        A a1 = (A) bf.getBean("A");
        System.out.println(a1.getName());
    }
}
