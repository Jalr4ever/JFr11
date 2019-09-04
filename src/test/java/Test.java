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

    @org.junit.Test
    public void test(){
        Map<String, Bean> config = ConfigManager.getConfig("/applicationContext.xml");
        System.out.println(config);
    }

    @org.junit.Test
    public void testII(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
        A a = (A) bf.getBean("A");
        B b = (B) bf.getBean("B");
        
        System.out.println(a.getName());

    }
}
