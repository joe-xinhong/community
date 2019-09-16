package lift.miao.community.enums;
/**
* @Description:    通知状态枚举
* @Author:         Joe
* @CreateDate:     2019/9/16 15:47
*/
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
