package lift.miao.community.service;

import lift.miao.community.provider.QCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

/**
* @Description:    腾讯云文件存储测试
* @Author:         Joe
* @CreateDate:     2019/9/17 17:45
*/
public class Testimg {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\imgmiao\\c.jpg");
        Long a= System.currentTimeMillis();

        Long b= System.currentTimeMillis();
        Long c = b-a;
        System.out.println("b-a="+c/1000);
    }
}
