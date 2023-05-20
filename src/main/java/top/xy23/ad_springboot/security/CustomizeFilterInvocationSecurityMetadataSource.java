package top.xy23.ad_springboot.security;

import cn.hutool.core.text.AntPathMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 根据请求，查询数据库，看看这个请求是那些角色能访问
 *
 * @author XiaoYang
 * @date 2023/5/20 12:45
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

//    @Autowired
//    SysPermissionMapper sysPermissionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 全放行
        return null;
//        // 查询具体某个接口的权限
//        List<SysPermission> permissionList =  sysPermissionMapper.selectListByPath(requestUrl);
//        if(permissionList == null || permissionList.size() == 0){
//            // 请求路径没有配置权限，表明该请求接口可以任意访问
//            return null;
//        }
//        String[] attributes = new String[permissionList.size()];
//        for(int i = 0;i<permissionList.size();i++){
//            attributes[i] = permissionList.get(i).getPermissionCode();
//        }
//        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
