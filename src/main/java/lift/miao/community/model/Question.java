package lift.miao.community.model;

import lombok.Data;

/**
* @Description:    问题实体
* @Author:         Joe
* @CreateDate:     2019/9/6 17:30
*/
@Data
public class Question {

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

}
