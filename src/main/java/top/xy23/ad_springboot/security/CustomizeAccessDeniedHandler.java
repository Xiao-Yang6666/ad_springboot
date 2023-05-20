package top.xy23.ad_springboot.security;

import com.alibaba.fastjson2.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.xy23.ad_springboot.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查看是否有对应的权限和功能
 *
 * @author XiaoYang
 * @date 2023/5/20 8:49
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(R.failure("权限不足,请联系管理员")));
    }

}