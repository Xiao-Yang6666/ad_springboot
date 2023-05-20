package top.xy23.ad_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.xy23.ad_springboot.controllers.from.SysLoginFrom;
import top.xy23.ad_springboot.service.SysUserService;
import top.xy23.ad_springboot.utils.R;

@SpringBootTest
class AdSpringbootApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    void contextLoads() {
        SysLoginFrom sysLoginFrom = new SysLoginFrom();
        sysLoginFrom.setAccount("123456");
        sysLoginFrom.setPassword("password1");
        sysLoginFrom.setVerificationCode("13");
        R r = sysUserService.sysLogin(sysLoginFrom);
        System.out.println(r);
    }

}
