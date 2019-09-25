package lift.miao.community.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:    照片
* @Author:         Joe
* @CreateDate:     2019/9/25 11:22
*/
@Controller
@Api(value = "个人中心接口",description = "个人中心接口")
public class PhotosController {

    @RequestMapping(value = "/photo/{action}")
    public String photos(HttpServletRequest request,
                         @PathVariable(name = "action")String action,
                         Model model,
                         @RequestParam(name = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        model.addAttribute("sectionName","安静图涂");
        return "photos";
    }
}
