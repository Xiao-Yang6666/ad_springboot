package top.xy23.ad_springboot.exception;

/**
 * 工具类异常
 *
 * @author XiaoYang
 * @date 2023/3/30 20:51
 */
public class UtilException extends RuntimeException {
    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
