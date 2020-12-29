package com.tangv.feature.util;

import com.tangv.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/29 19:32
 * Description: redis分布式锁
 */
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private final long TIME_OUT = 30*1000;

    public boolean lock(String key) {

        long currentTime = DateUtil.getNowStemp();

        /**
         * 通过setnx命令设置(key, currentTime + TIME_OUT),返回true说明加锁成功，false加锁失败（需要另作判断）；
         */
        Boolean locked = redisTemplate.opsForValue().setIfAbsent(key, currentTime + TIME_OUT);

        if (locked) {
            redisTemplate.expire(key,TIME_OUT, TimeUnit.MILLISECONDS);
            return true;
        }else {
            /**
             * redis中已存在该key:
             * 获取key值，存的是当时设置的时间戳
             */
            Object lockValue1 = redisTemplate.opsForValue().get(key);
            /**
             * 如果当前时间大于value1，说明之前的锁拥有者已经过期了，可以进一步判断是否可以获得锁
             */
            if (lockValue1 != null && currentTime>(long)lockValue1) {
                /**
                 * 通过getset命令设置key、value值，并且返回旧值lockValue2;
                 */
                Object lockValue2 = redisTemplate.opsForValue().getAndSet(key, currentTime + TIME_OUT);
                /**
                 * 如果值为空，说明锁已被释放，可以获取锁；
                 * 如果value1=value2，说明中间没有其他线程获取到过该锁，可以获取锁
                 * 否则不能获取锁
                 */
                if (lockValue2 == null || (long) lockValue1 == (long) lockValue2) {
                    return true;
                }
            }
        }
        return false;
    }

    public void unlock(String key) {
        redisTemplate.delete(key);
    }

}