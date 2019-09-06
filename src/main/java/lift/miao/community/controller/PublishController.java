package lift.miao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Description:    问题发布
* @Author:         Joe
* @CreateDate:     2019/9/6 16:16
*/
@Controller
public class PublishController {

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
}
