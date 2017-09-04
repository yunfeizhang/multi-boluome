package boluome.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Package: boluome.dao
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/23 上午9:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BoluomeDaoApplication.class)
public class UserDaoTest{
    org.slf4j.Logger log = LoggerFactory.getLogger(BoluomeDaoApplication.class);

    @Resource
    RedisTemplate redisTemplate;
    /**
     * 测试Redis访问
     */
    @Test
    public void testRedisAccessible(){
        log.debug("*************************** testRedisAccessible:getFromRedis");
        String nowTime = System.currentTimeMillis()+"";
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        ops.set("nowTime",nowTime);
        if(nowTime.equalsIgnoreCase(ops.get("nowTime"))){
            log.debug("***************************  testRedisAccessible:test OK");
        }else{
            log.debug("*************************** testRedisAccessible:test Fail");
        }
    }
}