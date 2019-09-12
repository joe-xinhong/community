package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
@Api(value = "主页数据接口",description = "主页数据接口")
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        /*问题列表*/
        PaginationDTO pagination = questionService.list(page,pageSize);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
