package lift.miao.community.dto;

import lombok.Data;

/**
* @Description:    通知传输模型
* @Author:         Joe
* @CreateDate:     2019/9/16 16:11
*/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;
    //问题id
    private Long outerId;
    private String typeName;
    private Integer type;
}
