package com.shf.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
//        verifyCode("13434444444");
        getRedisCode("13434444444","660180");
    }

    public static void getRedisCode(String phone,String code){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String codeKey="VerifyCode"+phone+":code";
        String redisCode = jedis.get(codeKey);
        System.out.println(redisCode);
        if (redisCode.equals(code)){
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        jedis.close();
    }

    public static void verifyCode(String phone){
//        连接jedis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        手机发送次数
        String countKey="VerifyCode"+phone+":count";
//        验证码key
        String codeKey="VerifyCode"+phone+":code";

//        手机号每天只能发送3次
        String count = jedis.get(countKey);
        if (count==null){
            jedis.setex(countKey,24*60*60,"1");
        } else if (Integer.parseInt(count)<=2){
            jedis.incr(countKey);
        } else if (Integer.parseInt(count)>2) {
            System.out.println("今天发送此时已经超过三次");
        }
        String vcode=getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
    }

    public static String getCode(){
        Random random = new Random();
        String code ="";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code+=rand;
        }
        return code;
    }
}
