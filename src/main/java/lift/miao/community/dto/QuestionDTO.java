package lift.miao.community.dto;

import lift.miao.community.model.User;
import lombok.Data;

/**
* @Description:    问题（传输型数据）
* @Author:         Joe
* @CreateDate:     2019/9/9 16:54
*/
@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
