import com.with.bai.dao.UserDao;
import com.with.bai.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml", "classpath:applicationContext-druid.xml", "classpath:applicationContext-service.xml","classpath:spring-mvc.xml"})
public class test {
    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        User user = userDao.getUserByid(1);
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = userDao.getuserByemail("chx@qq.com");
        System.out.println(user);
    }

    @Test
    public void test3(){
        User user=new User();
        user.setEmail("test@qq.com");
        user.setIDNumber("441581199612150018");
        userDao.addUser(user);
    }
}
