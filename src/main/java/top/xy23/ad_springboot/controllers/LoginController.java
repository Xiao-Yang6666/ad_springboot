package top.xy23.ad_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.xy23.ad_springboot.controllers.from.SysLoginFrom;
import top.xy23.ad_springboot.service.SysUserService;
import top.xy23.ad_springboot.utils.R;
import top.xy23.ad_springboot.utils.RedisUtil;

import javax.validation.Valid;

/**
 * @author XiaoYang
 * @date 2023/5/20 8:36
 */
@RestController
public class LoginController extends BasController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/sysLogin")
    public R sysLogin(@RequestBody @Valid SysLoginFrom sysLoginFrom) {
        return sysUserService.sysLogin(sysLoginFrom);
    }

    @GetMapping("/test")
    public R sysLogin() {
        return R.ok("你好");
    }


//    @GetMapping("/logOut")
//    public Result logOut(HttpServletRequest request, HttpServletResponse
//            response, Principal principal) {
//        //清除spring security用户认证信息
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //清除redis中的token信息
//        redisUtil.delKey(principal.getName());
//        if (null != auth) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return new Result(MessageConstant.SUCCESS_CODE, MessageConstant.LOGOUT_SUCCESS);
//    }

}
