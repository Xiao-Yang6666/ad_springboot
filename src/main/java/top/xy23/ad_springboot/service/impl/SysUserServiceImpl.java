package top.xy23.ad_springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.xy23.ad_springboot.controllers.from.SysLoginFrom;
import top.xy23.ad_springboot.models.SysUserEntity;
import top.xy23.ad_springboot.repository.SysUserRepository;
import top.xy23.ad_springboot.security.service.UserDetailServiceImpl;
import top.xy23.ad_springboot.service.SysUserService;
import top.xy23.ad_springboot.utils.JwtTokenUtil;
import top.xy23.ad_springboot.utils.R;

import java.util.Date;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:12
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public R sysLogin(SysLoginFrom sysLoginFrom) {
        UserDetails userDetails = userDetailService.loadUserByUsername(sysLoginFrom.getAccount());
        SysUserEntity sysUserEntity = BeanUtil.copyProperties(userDetails, SysUserEntity.class);
        // 校验密码 password1 添加盐的方式 todo
        if (!sysUserEntity.getPassword().equals(sysLoginFrom.getPassword())) {
            System.out.println("密码错误");
        }

        return baseLogin(userDetails, sysUserEntity);
    }

    /**
     * 下发token
     */
    private R baseLogin(UserDetails userDetails, SysUserEntity sysUserEntity) {
        String token = JwtTokenUtil.generateToken(userDetails);
        String loginTime = DateUtil.formatDateTime(new Date());
        String nickname = sysUserEntity.getNickname();
        String avatar = sysUserEntity.getAvatar();

        return R.ok("登录成功")
                .put("token", token)
                .put("loginTime", loginTime)
                .put("nickname", nickname)
                .put("avatar", avatar);
    }

//    @Override
//    public Result sysLogin(SysLoginFrom sysLoginVo) {
//        //系统用户登录以*开头
//        String username = "*" + sysLoginVo.getLoginName();
//        //走SpringSecurity的方法
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        //利用hutool工具类转成数据库中的对象
//        SysUserEntity sysUserEntity = BeanUtil.copyProperties(userDetails, SysUserEntity.class);
//        //获取MD5加密值
//        String md5HexPassword = DigestUtils.md5Hex(sysLoginVo.getLoginName() + sysLoginVo.getPassword() + sysUserEntity.getSalt());
//        logger.debug("获取到md5加密值");
//        //拿着获取到的用户名和密码验证一遍
//        if(userMapper.checkSysUserLogin(sysLoginVo.getLoginName(),md5HexPassword) ==  0 ) {
//            logger.debug("用户名：{}和密码：{}校验未通过",sysLoginVo.getLoginName(),md5HexPassword);
//            return Result.fail("用户名或密码错误");
//        };
//        //这里会调用SysUserEntity实体类中的isEnabled方法，根据业务逻辑自行判断账户状态
//        if(userDetails.isEnabled()) {
//            return Result.fail("该帐号已被禁用，请联系管理员！");
//        }
//        return baseLogin(userDetails,null, sysLoginVo,"sysUser");
//    }
//
//
//    private Result baseLogin(UserDetails userDetails, LoginVo loginVo,SysLoginFrom sysLoginVo, String userRole) {
//        HashMap<Object,Object> map = new HashMap<>(16);
//        //生成token
//        String token = tokenUtil.generateToken(userDetails,userRole);
//        //将token和登陆时间放入map
//        map.put("token", token);
//        map.put("loginTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//        try {
//            if("mpUser".equals(userRole)) {
//                map.put("customerName", loginVo.getCustomerName());
//                map.put("identityCard", loginVo.getIdentityCard());
//                //更新用户登录时间
//                userMapper.updateUserInfo(map);
//                //存到登录日志
//                map.put("id",System.currentTimeMillis());
//                userMapper.insertLoginLog(map);
//                //存进redis
//                String redisKey = "#" + loginVo.getIdentityCard();
//                redisUtil.setObjectTime(redisKey,token,expireMinutes);
//            }else {
//                map.put("loginName", sysLoginVo.getLoginName());
////	    		userMapper.updateSysUserInfo(map);不能更改对面的表
//                //查询当前系统用户的信息
//                Map<String,Object> sysUserInfo = userMapper.querySysUserBylogName(map);
//                if(!sysUserInfo.get("user_type").equals(sysLoginVo.getUserType())) {
//                    return Result.fail("当前用户不属于该角色");
//                }
//                //如果是商家返回机构信息
//                if("04".equals(sysLoginVo.getUserType())) {
//                    //查询当前系统用户对应的机构信息
//                    Map<String,Object> resultMap =  userMapper.getSysUserInfoDetail(sysUserInfo);
//                    if(resultMap != null) {
//                        map.put("businessCircleId",resultMap.get("business_circle_id"));
//                        map.put("id",resultMap.get("id"));
//                        map.put("orgId",resultMap.get("org_id"));
//                        map.put("sceneId",resultMap.get("scene_id"));
//                        map.put("orgName",resultMap.get("org_name"));
//                        map.put("orgPic",resultMap.get("org_pic"));
//                    }
//                }
//                //如果是商家返回机构信息
//                if("03".equals(sysLoginVo.getUserType())) {
//                    //查询当前系统用户对应的机构信息
//                    String deptName=  userMapper.getSysUserDeptInfoDetail(sysUserInfo);
//                    map.put("deptName",deptName);
//                }
//                map.put("userName",sysUserInfo.get("user_name"));
//                map.put("userId",sysUserInfo.get("user_id"));
//                //存进redis
//                String redisKey = "*" + sysLoginVo.getLoginName();
//                redisUtil.setObjectTime(redisKey, token, expireMinutes);
//            }
//
//            logger.debug("用户{}登录成功，信息已保存至redis和数据库",userDetails.getUsername());
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            return Result.success(MessageConstant.LOGIN_SUCCESS, map);
//        }catch(Exception e) {
//            if("mpUser".equals(userRole)) {
//                redisUtil.delKey(loginVo.getCustomerName());
//            }else {
//                redisUtil.delKey(sysLoginVo.getLoginName());
//            }
//            logger.debug("用户{}登录失败，信息已回滚,{}",userDetails.getUsername(),e.getMessage());
//            return Result.fail(MessageConstant.LOGIN_FAIL);
//        }
}
