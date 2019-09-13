package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.dto.QuestionDTO;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Description:    问题处理
* @Author:         Joe
* @CreateDate:     2019/9/11 16:07
*/
@Controller
@Api(value = "问题详情接口",description = "问题详情接口")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
