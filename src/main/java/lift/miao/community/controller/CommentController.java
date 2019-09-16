package lift.miao.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lift.miao.community.dto.CommentCreateDTO;
import lift.miao.community.dto.CommentDTO;
import lift.miao.community.dto.ResultDTO;
import lift.miao.community.enums.CommentTypeEnum;
import lift.miao.community.exception.CustomizeErrorCode;
import lift.miao.community.model.Comment;
import lift.miao.community.model.User;
import lift.miao.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //StringUtils.isBlank(commentCreateDTO.getContent())代替了(commentCreateDTO.getContent()==null||commentCreateDTO.getContent()=="")
        if(commentCreateDTO==null||StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @ApiOperation(value = "查询评论的二级信息",notes = "查询评论的二级信息")
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT.getType());
        return ResultDTO.okOf(commentDTOS);
    }
}
