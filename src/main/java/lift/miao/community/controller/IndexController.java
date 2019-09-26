package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.model.UserExample;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @Description:    主页及登录页入口
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
@Api(value = "主页数据接口",description = "主页数据接口")
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                        @RequestParam(name = "search",required = false)String search){
        /*问题列表*/
        PaginationDTO pagination = questionService.list(search,page,pageSize);
        model.addAttribute("pagination",pagination);
        /*分页时携带*/
        model.addAttribute("search",search);
        return "index";
    }

    /**
     * 跳转登录
     * @return
     */
    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    /**
     * 普通用户登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name")String name,
                        @RequestParam(value = "accountId")String accountId,
                        @RequestParam(name = "search",required = false)String search,
                        HttpServletRequest request,
                        Model model){
        model.addAttribute("name",name);
        model.addAttribute("accountId",accountId);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null && users.size()>0){
            request.getSession().setAttribute("user",users.get(0));
            /*问题列表*/
            PaginationDTO pagination = questionService.list(search,1,5);
            model.addAttribute("pagination",pagination);
            /*分页时携带*/
            model.addAttribute("search",search);
            return "index";
        }
       return "login";

    }
}
