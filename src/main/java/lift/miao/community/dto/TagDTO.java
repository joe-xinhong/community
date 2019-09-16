package lift.miao.community.dto;

import lombok.Data;

import java.util.List;

/**
* @Description:    标签
* @Author:         Joe
* @CreateDate:     2019/9/16 13:07
*/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
