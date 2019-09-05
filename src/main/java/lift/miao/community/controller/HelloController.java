package lift.miao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        return "miao";
    }
}
