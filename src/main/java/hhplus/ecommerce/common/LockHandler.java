package hhplus.ecommerce.common;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LockHandler {

    private static final String LOCK_KEY_PREFIX = "LOCK";
    private final RedissonClient redissonClient;

    public LockHandler(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void lock(String key, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + key);
        try {
            boolean available = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            if (!available) {
                throw new InterruptedException("LOCK_ACQUISITION_FAILED");
            }
//            log.info("Lock acquired for key: {}", LOCK_KEY_PREFIX + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void unlock(String key) {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + key);
        lock.unlock();
//        log.info("Lock acquired for key: {}", LOCK_KEY_PREFIX + key);
    }
}