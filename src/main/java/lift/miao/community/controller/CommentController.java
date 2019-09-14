package lift.miao.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lift.miao.community.dto.CommentCreateDTO;
import lift.miao.community.dto.ResultDTO;
import lift.miao.community.exception.CustomizeErrorCode;
import lift.miao.community.mapper.CommentMapper;
import lift.miao.community.model.Comment;
import lift.miao.community.model.User;
import lift.miao.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
* @Description:    评论处理
* @Author:         Joe
* @CreateDate:     2019/9/13 0:21
*/
@Controller
@Api(value = "评论接口",description = "评论接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @ApiOperation(value = "添加评论",notes = "增加评论")
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        System.out.println(commentCreateDTO.getParentId());
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
