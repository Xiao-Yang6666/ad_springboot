package top.xy23.ad_springboot.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.xy23.ad_springboot.models.SysUserEntity;
import top.xy23.ad_springboot.repository.SysUserRepository;

/**
 * @author XiaoYang
 * @date 2023/5/20 11:09
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user = sysUserRepository.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("账号或者密码错误！");
        }

        return user;
    }
}
