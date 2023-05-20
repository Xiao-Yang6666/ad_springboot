package top.xy23.ad_springboot.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:04
 */
@Component
public class RedisUtil {
    private static HashMap<String, Object> hashMap = new HashMap<>();

    public boolean hasKey(String password) {
        return hashMap.containsKey(password);
    }

    public Object getObject(String password) {
        return hashMap.get(password);
    }
}
