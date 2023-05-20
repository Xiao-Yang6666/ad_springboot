package top.xy23.ad_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xy23.ad_springboot.models.SysUserEntity;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:37
 */
public interface SysUserRepository extends JpaRepository<SysUserEntity, String> {
    SysUserEntity findByAccount(String account);
}
