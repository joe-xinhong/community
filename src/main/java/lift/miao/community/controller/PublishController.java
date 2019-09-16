package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.cache.TagCache;
import lift.miao.community.dto.QuestionDTO;
import lift.miao.community.mapper.QuestionMapper;
import lift.miao.community.model.Question;
import lift.miao.community.model.User;
import lift.miao.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:    问题发布
* @Author:         Joe
* @CreateDate:     2019/9/6 16:16
*/
@Controller
@Api(value = "问题处理接口",description = "问题处理接口")
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**
     * 跳转至编辑页
     * @param id
     * @return
     */
    @RequestMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false)String title,
                            @RequestParam(value = "description",required = false)String description,
                            @RequestParam(value = "tag",required = false)String tag,
                            @RequestParam(value = "id",required = false)Long id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags",TagCache.get());
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        String s = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(s)){
            model.addAttribute("error","输入非法标签"+s);
            return "publish";
        }
        User user = (User)request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
