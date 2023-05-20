package top.xy23.ad_springboot.service;

import top.xy23.ad_springboot.controllers.from.SysLoginFrom;
import top.xy23.ad_springboot.utils.R;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:11
 */
public interface SysUserService {
    /**
     * 登录
     *
     * @param sysLoginFrom 表单
     * @return
     */
    R sysLogin(SysLoginFrom sysLoginFrom);
}
