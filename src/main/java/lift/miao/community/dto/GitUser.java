package lift.miao.community.dto;

import lombok.Data;

/**
* @Description:    码云授权用户信息
* @Author:         Joe
* @CreateDate:     2019/9/5 16:58
*/
@Data
public class GitUser {

    private String name;

    private Long id;

    private String bio;

    private String avatar_url;
}
