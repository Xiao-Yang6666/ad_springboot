package top.xy23.ad_springboot.controllers.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:10
 */
@Data
public class SysLoginFrom {
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "(^[a-zA-Z0-9]{6,10}$)|(^\\d{11}$)|(^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$)",
            message = "账号格式不正确，必须为6-10位数字，手机号或者邮箱")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$", message = "验证码格式不正确，必须为6位字符，只能包含字母和数字")
    private String verificationCode;
}
