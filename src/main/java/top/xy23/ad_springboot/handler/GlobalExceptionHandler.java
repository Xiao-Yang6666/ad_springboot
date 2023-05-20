package top.xy23.ad_springboot.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.xy23.ad_springboot.enums.RetCodeEnums;
import top.xy23.ad_springboot.utils.R;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author XiaoYang
 * @date 2023/5/20 10:39
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException ex) {
        String message = "";
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
//            String field = violation.getPropertyPath().toString();
            message = violation.getMessage();
        }
        return R.err(RetCodeEnums.PARAM.getCode(), message);
    }
}
