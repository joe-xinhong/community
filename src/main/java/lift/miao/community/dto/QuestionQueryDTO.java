package lift.miao.community.dto;

import lombok.Data;

/**
* @Description:    问题搜索传输型
* @Author:         Joe
* @CreateDate:     2019/9/18 20:19
*/
@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
    private Integer page;
    private Integer pageSize;
}
