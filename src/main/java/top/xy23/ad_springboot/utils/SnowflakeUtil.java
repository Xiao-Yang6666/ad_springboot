package top.xy23.ad_springboot.utils;

import java.security.SecureRandom;

/**
 * @author XiaoYang
 * @date 2023/5/20 9:44
 */
public class SnowflakeUtil {
    private static final long EPOCH_STAMP = 1262275200000L;//开始时间戳，可自定义
    private static final long SEQUENCE_BIT = 12L;//序号位 12
    private static final long MACHINE_BIT = 5L;//机器码位 5
    private static final long DATA_CENTER_BIT = 5L;//数据中心 5
    private static final long MAX_SEQUENCE_NUM = -1L ^ (-1L << SEQUENCE_BIT);
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT + DATA_CENTER_BIT;
    private static volatile SnowflakeUtil instance;
    private final long machineId;//机器ID
    private final long dataCenterId;//数据中心ID
    private long sequence = 0L;
    private long lastTimestamp = -1L;//上次生成ID的时间戳

    private SnowflakeUtil(long machineId, long dataCenterId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(String.format("machine id can't be greater than %d or less than 0", MAX_MACHINE_NUM));
        }
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("data center id can't be greater than %d or less than 0", MAX_DATA_CENTER_NUM));
        }
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    public static void main(String[] args) {
        System.out.println(generateId());
    }

    public static SnowflakeUtil getInstance(long machineId, long dataCenterId) {
        if (instance == null) {
            synchronized (SnowflakeUtil.class) {
                if (instance == null) {
                    instance = new SnowflakeUtil(machineId, dataCenterId);
                }
            }
        }
        return instance;
    }

    public static long generateId() {
        try {
            return getInstance(0L, 0L).nextValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized long nextValue() throws Exception {
        String os = System.getProperty("os.name");
        SecureRandom secureRandom;
        if (os.toLowerCase().startsWith("win")) {
            // windows机器用
            secureRandom = SecureRandom.getInstanceStrong();
        } else {
            // linux机器用
            secureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
        }
        long currentTimeMillis = this.currentTimeMillis();
        if (currentTimeMillis < this.lastTimestamp) {
            throw new Exception(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", (this.lastTimestamp - currentTimeMillis)));
        }

        if (this.lastTimestamp == currentTimeMillis) {
            this.sequence = (this.sequence + 1) & MAX_SEQUENCE_NUM;
            if (this.sequence == 0) {
                this.sequence = secureRandom.nextInt(Long.valueOf(SEQUENCE_BIT).intValue());
                currentTimeMillis = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = secureRandom.nextInt(Long.valueOf(SEQUENCE_BIT).intValue());
        }
        this.lastTimestamp = currentTimeMillis;

        // 64 Bit ID (42(Millis)+5(Data Center ID)+5(Machine ID)+12(Repeat Sequence Summation))
        return ((currentTimeMillis - EPOCH_STAMP) << TIMESTAMP_LEFT)
                | (this.dataCenterId << DATA_CENTER_LEFT)
                | (this.machineId << MACHINE_LEFT)
                | this.sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long currentTimeMillis = this.currentTimeMillis();
        while (currentTimeMillis <= lastTimestamp) {
            currentTimeMillis = this.currentTimeMillis();
        }
        return currentTimeMillis;
    }

    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
