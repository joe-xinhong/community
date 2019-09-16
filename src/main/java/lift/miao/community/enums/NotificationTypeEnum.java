package lift.miao.community.enums;
/**
* @Description:    通知枚举
* @Author:         Joe
* @CreateDate:     2019/9/16 15:38
*/
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum:NotificationTypeEnum.values()){
            if (notificationTypeEnum.getType() == type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
