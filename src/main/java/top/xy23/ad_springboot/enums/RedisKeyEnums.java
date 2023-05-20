package top.xy23.ad_springboot.enums;

import java.util.concurrent.TimeUnit;

/**
 * @author XiaoYang
 * @date 2023/5/20 09:01
 */
public enum RedisKeyEnums {
    NM("", -1);


    private final String KEY;

    private final long TIMEOUT;

    private final TimeUnit UNIT;

    RedisKeyEnums(String KEY, long TIMEOUT) {
        this.KEY = KEY;
        this.TIMEOUT = TIMEOUT;
        this.UNIT = TimeUnit.MILLISECONDS;
    }

    RedisKeyEnums(String KEY, long TIMEOUT, TimeUnit UNIT) {
        this.KEY = KEY;
        this.TIMEOUT = TIMEOUT;
        this.UNIT = UNIT;
    }

    public String getKEY() {
        return KEY;
    }


    public long getTIMEOUT() {
        return TIMEOUT;
    }


    public TimeUnit getUNIT() {
        return UNIT;
    }

}
