package top.xy23.ad_springboot.models;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author XiaoYang
 * @date 2023/5/20 10:21
 */
@Data
@Entity
@Table(name = "sys_user", schema = "ad")
public class SysUserEntity implements UserDetails {
    @GeneratedValue
    @Id
    @Column(name = "userId")
    private String userId;
    @Basic
    @Column(name = "account")
    private String account;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "userType")
    private String userType;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phonenumber")
    private String phonenumber;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "loginTime")
    private Timestamp loginTime;
    @Basic
    @Column(name = "createTime")
    private Timestamp createTime;
    @Basic
    @Column(name = "updateTime")
    private Timestamp updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return !StringUtils.isNotBlank(this.getStatus()) || !"0".equals(this.getStatus());
    }

    @Override
    public String getUsername() {
        return this.getUserId();
    }
}
