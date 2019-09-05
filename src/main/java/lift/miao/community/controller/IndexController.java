package lift.miao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
