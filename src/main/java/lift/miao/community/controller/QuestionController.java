package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.dto.CommentDTO;
import lift.miao.community.dto.QuestionDTO;
import lift.miao.community.enums.CommentTypeEnum;
import lift.miao.community.service.CommentService;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    @Autowired
    private CommentService commentService;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id,CommentTypeEnum.QUESTION.getType());
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
