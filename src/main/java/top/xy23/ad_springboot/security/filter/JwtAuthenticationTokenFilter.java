package top.xy23.ad_springboot.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.xy23.ad_springboot.security.service.UserDetailServiceImpl;
import top.xy23.ad_springboot.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个过滤器用来判断JWT是否有效
 *
 * @author XiaoYang
 * @date 2023/5/20 12:34
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * 直接将我们前面写好的service注入进来，通过service获取到当前用户的权限
     */
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取当请求头中的token，其实这里多余，完全可以使用HttpServletRequest来获取
        String authToken = httpServletRequest.getHeader("UserToken");

        // 获取到当前用户的account
        String account = JwtUtils.getMemberAccountByJwtToken(httpServletRequest);

        System.out.println("自定义JWT过滤器获得用户名为" + account);

        // 当token中的username不为空时进行验证token是否是有效的token
        if (!account.equals("") && SecurityContextHolder.getContext().getAuthentication() == null) {
            // token中username不为空，并且Context中的认证为空，进行token验证

            // 获取到用户的信息，也就是获取到用户的权限
            UserDetails userDetails = this.userDetailService.loadUserByUsername(account);

            if (JwtUtils.checkToken(authToken)) {   // 验证当前token是否有效

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                //将authentication放入SecurityContextHolder中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 放行给下个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}