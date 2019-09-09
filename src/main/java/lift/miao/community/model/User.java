package lift.miao.community.model;

import lombok.Data;

/**
* @Description:    登录用户
* @Author:         Joe
* @CreateDate:     2019/9/6 12:36
*/
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
