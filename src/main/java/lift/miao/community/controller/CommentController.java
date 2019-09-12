package lift.miao.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lift.miao.community.dto.CommentDTO;
import lift.miao.community.mapper.CommentMapper;
import lift.miao.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;

/**
* @Description:    评论处理
* @Author:         Joe
* @CreateDate:     2019/9/13 0:21
*/
@Controller
@Api(value = "评论接口",description = "评论接口")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ApiOperation(value = "添加评论",notes = "增加评论")
    @RequestMapping(value = "/comment",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insert(comment);
        return null;
    }
}
