//package top.xy23.ad_springboot.handler;
//
//import io.micrometer.common.util.StringUtils;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Component;
//import top.xy23.ad_springboot.utils.JwtTokenUtil;
//import top.xy23.ad_springboot.utils.RedisUtil;
//
//import java.io.IOException;
//
///**
// * @author XiaoYang
// * @date 2023/5/20 8:53
// */
//@Component
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader(tokenHeader);
//        if (StringUtils.isNotEmpty(authHeader)) {
//            String password = null;
//            try {
//                logger.debug("解析token");
//                password = JwtTokenUtil.getUserNameFromToken(authHeader);
//            } catch (Exception e) {
//                throw new RuntimeException("token解密失败");
//            }
//            // token存在，但是未登录
//            if (null != password && null == SecurityContextHolder.getContext().getAuthentication()) {
//                // 登录
//                logger.debug("登录loadUserByUsername的password");
//                UserDetails userDetails = userDetailsService.loadUserByUsername(password);
//                // 验证redis中的token是否过期，重新设置用户对象
//                if (redisUtil.hasKey(password)) {
//                    String redisToken = (String) redisUtil.getObject(password);
//                    // 判断携带的token和redis中的是否一致，一致则放行，不一致则以redis为准，退出请求
//                    if (authHeader.equals(redisToken)) {
//                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    }
//                } else {
//                    // 清除spring security用户认证信息
//                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                    if (null != auth) {
//                        new SecurityContextLogoutHandler().logout(request, response, auth);
//                    }
//                }
//
////					if (jwtTokenUtil.validateToken(authHeader, userDetails)) {
////						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////					}
//            }
//        }
//        chain.doFilter(request, response);
//    }
//
//}
