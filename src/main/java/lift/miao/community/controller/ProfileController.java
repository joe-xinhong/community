package lift.miao.community.controller;

import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:    个人中心
* @Author:         Joe
* @CreateDate:     2019/9/10 17:13
*/
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action")String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, pageSize);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
