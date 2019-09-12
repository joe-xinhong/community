package lift.miao.community.exception;
/**
* @Description:    业务部分的异常错误信息处理实现(枚举)
* @Author:         Joe
* @CreateDate:     2019/9/12 17:49
*/
public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试?");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
