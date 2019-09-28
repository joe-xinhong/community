package lift.miao.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling/*定时任务注解*/
@MapperScan(basePackages = "lift.miao.community.mapper")
//@MapperScan(value = "lift.miao.community.mapper")//配置需要扫描的mapper包所在位置
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
