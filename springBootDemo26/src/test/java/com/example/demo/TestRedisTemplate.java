package com.example.demo;

import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString()
    {
        // 测试保存、获取获取String类型
        // 两次set同样的key则value会覆盖
        redisTemplate.opsForValue().set("key1", "welcome");
        redisTemplate.opsForValue().set("key1", "codelabx");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        Assert.assertEquals("codelabx", redisTemplate.opsForValue().get("key1"));
    }

    @Test
    public void testObj()
    {
        // 测试保存、获取一个对象类型
        User user = new User("admin@codelabx.com", "welcome", "to", "codelabx", "2019-11-06");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.codelabx", user);
        User u = operations.get("com.codelabx");
        System.out.println("user: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException
    {
        User user = new User("admin@codelabx.com", "welcome", "to", "codelabx", "2019-11-06");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS); // 保存数据时，手工指定过期时间100毫秒
        Thread.sleep(1000); // 线程休息1000毫秒
        boolean exists = redisTemplate.hasKey("expire");
        if (exists)
        {
            System.out.println("exists is true");
        }
        else
        {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete()
    {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("codelabx", "welcome");
        redisTemplate.delete("codelabx"); // 删除数据
        boolean exists = redisTemplate.hasKey("codelabx");
        if (exists)
        {
            System.out.println("exists is true");
        }
        else
        {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash()
    {
        String key = "key";
        redisTemplate.delete(key);

        // Redis存储一个key会有一个最小内存，不管你存的这个key的数据量多小都不会低于这个内存，因此合理的使用Hash可以帮我们节省很多内存。
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, "hashKey1", "hashValue1"); // 参数1:Redis存储的主键。参数2:哈希数据的key。参数3:哈希数据key对应的value
        hash.put(key, "hashKey2", "hashValue2"); // 这样一组哈希数就可以共用同一个Redis主键来存储

        String value1 = (String) hash.get(key, "hashKey1");
        String value2 = (String) hash.get(key, "hashKey2");
        System.out.println("hash value1 :" + value1);
        System.out.println("hash value2 :" + value2);
    }


    @Test
    public void testList()
    {
        String key = "list";
        redisTemplate.delete(key);

        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush(key, "value1");
        list.leftPush(key, "value2");
        list.leftPush(key, "value3");

        // 移除并返回list中传入的参数key对应的第一个元素
        String value = (String) list.leftPop(key);
        System.out.println("list leftPop value :" + value.toString());

        // 返回传入索引位置的元素(第一个是0)
        List<String> values = list.range(key, 0, 1);
        for (String v : values)
        {
            System.out.println("list range :" + v);
        }
    }


    @Test
    public void testSet()
    {
        String key = "set";
        redisTemplate.delete(key);

        // 存入 读取set类型
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, "value1");
        set.add(key, "value2");
        set.add(key, "value2");
        set.add(key, "value3");

        Set<String> values = set.members(key);
        for (String v : values)
        {
            System.out.println("set value :" + v);
        }
    }

    @Test
    public void testSetMore()
    {
        String key1 = "setMore1";
        String key2 = "setMore2";
        redisTemplate.delete(key1);
        redisTemplate.delete(key2);

        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key1, "value1");
        set.add(key1, "value2");
        set.add(key1, "value3");
        set.add(key1, "value4");
        set.add(key2, "value1");
        set.add(key2, "valueXXX");
        set.add(key2, "value2");

        // 返回set集合中传入key1对应的值中有哪些不同于key2对应的值
        Set<String> diffs = set.difference(key1, key2);
        for (String v : diffs)
        {
            System.out.println("diffs set value :" + v);
        }

        String key3 = "setMore3";
        String key4 = "setMore4";
        redisTemplate.delete(key3);
        redisTemplate.delete(key4);

        set.add(key3, "value1");
        set.add(key3, "value2");
        set.add(key3, "value3");
        set.add(key4, "value1");
        set.add(key4, "value2");
        set.add(key4, "valueXXX");

        // 返回set集合中传入的key1与key2对应的所有的值(自动去除重复)
        Set<String> unions = set.union(key3, key4);
        for (String v : unions)
        {
            System.out.println("unions value :" + v);
        }
    }


    @Test
    public void testZset()
    {
        String key = "zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key, "value1", 1);
        zset.add(key, "value2", 6);
        zset.add(key, "value3", 4);
        zset.add(key, "value4", 3);

        // 返回传入索引位置的元素(第一个是0)
        Set<String> zsets = zset.range(key, 0, 3);
        for (String v : zsets)
        {
            System.out.println("zset range value :" + v);
        }

        // 返回位于传入最大最小参数之间的Score值对应的元素
        Set<String> zsetB = zset.rangeByScore(key, 0, 4);
        for (String v : zsetB)
        {
            System.out.println("zset rangeByScore value :" + v);
        }
    }

}