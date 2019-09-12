package lift.miao.community.exception;

/**
 * @Description: 定义异常处理
 * @Author: Joe
 * @CreateDate: 2019/9/12 17:32
 */
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
