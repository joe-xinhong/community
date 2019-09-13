package lift.miao.community.exception;

/**
 * @Description: 定义异常处理
 * @Author: Joe
 * @CreateDate: 2019/9/12 17:32
 */
public class CustomizeException extends RuntimeException {

    private String message;

    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
