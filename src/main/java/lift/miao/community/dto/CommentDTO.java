package lift.miao.community.dto;

import lombok.Data;
/**
* @Description:    评论传输数据
* @Author:         Joe
* @CreateDate:     2019/9/13 0:32
*/
@Data
public class CommentDTO {

    private Long parentId;

    private String content;

    private Integer type;

}
