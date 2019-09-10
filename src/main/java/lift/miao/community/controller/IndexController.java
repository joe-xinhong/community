package lift.miao.community.controller;

import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0)
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        /*问题列表*/
        PaginationDTO pagination = questionService.list(page,pageSize);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
