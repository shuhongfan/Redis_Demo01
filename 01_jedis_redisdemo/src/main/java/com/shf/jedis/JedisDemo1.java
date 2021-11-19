package com.shf.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo1 {
    static Jedis jedis = new Jedis("127.0.0.1", 6379);
    public static void main(String[] args) {
        String s = jedis.ping();
        System.out.println(s);
    }

    @Test
    public void demo1(){
        Set<String> keys = jedis.keys("*");
        for (String key:keys){
            System.out.println(key);
        }
    }

    @Test
    public void demo2(){
        //        添加
        jedis.set("name","lucy");
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    public void demo3(){
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);
    }

    @Test
    public void demo4(){
        jedis.lpush("key1","lucy","mary","jack");
        List<String> lrange = jedis.lrange("key1", 0, -1);
        System.out.println(lrange);
    }

    @Test
    public void demo5(){
        jedis.sadd("names","lucy","jack");
        Set<String> names = jedis.smembers("names");
        System.out.println(names);
    }

    @Test
    public void demo6(){
        jedis.hset("users","age","20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }


    @Test
    public void demo7(){
        jedis.zadd("china",100d,"shanghai");
        Set<String> zrange = jedis.zrange("china", 0, -1);
        System.out.println(zrange);
    }
}
