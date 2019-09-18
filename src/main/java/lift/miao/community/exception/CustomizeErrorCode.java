package lift.miao.community.exception;
/**
* @Description:    业务部分的异常错误信息处理实现(枚举)
* @Author:         Joe
* @CreateDate:     2019/9/12 17:49
*/
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换个试试?"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或者评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请先登录"),
    SYS_ERROR(2004,"擦！服务器冒烟了。赔钱！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，换个试试！"),
    COMMENT_IS_EMPTY(2007,"回复的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"伙计，你这是在读别人的信息呢？"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是不翼而飞了！"),
    FILE_UPLOAD_FAIL(2010,"图片上传失败");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
