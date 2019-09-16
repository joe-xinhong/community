package lift.miao.community.service;

import lift.miao.community.dto.CommentDTO;
import lift.miao.community.enums.CommentTypeEnum;
import lift.miao.community.enums.NotificationTypeEnum;
import lift.miao.community.enums.NotificationStatusEnum;
import lift.miao.community.exception.CustomizeErrorCode;
import lift.miao.community.exception.CustomizeException;
import lift.miao.community.mapper.*;
import lift.miao.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional/*添加事务*/
    public void insert(Comment comment,User commentator) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw  new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andIdEqualTo(comment.getParentId());
            List<Comment> dbComments = commentMapper.selectByExample(commentExample);
            if(dbComments==null||dbComments.size()==0){
                throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            Question question = questionMapper.selectByPrimaryKey(dbComments.get(0).getParentId());
            if(question == null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);
            //创建通知
            createNotify(comment,dbComments.get(0).getCommentator(),commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_COMMENT,question.getId());
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            //创建通知
            createNotify(comment,question.getCreator(),commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_QUESTION,question.getId());
        }
    }

    /**
     * 回复或者评论后添加通知信息
     * @param comment
     */
    private void createNotify(Comment comment,Integer receiver,String notifierName,String outerTitle,NotificationTypeEnum notificationType,Long outerId){
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id,Integer type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments==null||comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users= userMapper.selectByExample(userExample);
        Map<Integer,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user ->user));

        //转换Comment 为 CommentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment->{
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
