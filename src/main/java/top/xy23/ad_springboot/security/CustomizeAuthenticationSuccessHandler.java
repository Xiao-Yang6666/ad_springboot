package top.xy23.ad_springboot.security;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.xy23.ad_springboot.enums.ResultCode;
import top.xy23.ad_springboot.models.SysUserEntity;
import top.xy23.ad_springboot.repository.SysUserRepository;
import top.xy23.ad_springboot.utils.JwtUtils;
import top.xy23.ad_springboot.utils.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功
 *
 * @author XiaoYang
 * @date 2023/5/20 12:30
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        SysUserEntity userDetails = (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUserEntity sysUser = sysUserRepository.findByAccount(userDetails.getUsername());
//        SysUser sysUser = mapper.selectOne(new QueryWrapper<SysUser>().eq("account",userDetails.getUsername()));
//        sysUser.setLastLoginTime(new Date());
//        sysUser.setUpdateTime(new Date());
//        sysUser.setUpdateUser(sysUser.getId());
//        mapper.update(sysUser,new QueryWrapper<SysUser>().eq("id",sysUser.getId()));

        // 根据用户的id和account生成token并返回
        String jwtToken = JwtUtils.getJwtToken(sysUser.getUserId(), sysUser.getAccount());
        Map<String, String> results = new HashMap<>();
        results.put("token", jwtToken);

        //返回json数据
        R result = R.defined(ResultCode.SUCCESS_login, results);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        // 把Json数据放入HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}