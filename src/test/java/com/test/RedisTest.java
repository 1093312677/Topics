package com.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.common.RedisTool;
import com.entity.Teacher;
import com.entity.Template;
import com.entity.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
	
	JedisPool pool;   
	Jedis jedis;   
	@Before   
	public void setUp() {   
	pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");    
	jedis = pool.getResource(); 
	jedis.auth("kone"); 
	jedis.set("name", "kone");
	  
	}   
	@Test
	public void redis() {
		System.out.println(jedis.get("name"));
		
		Jedis je = new Jedis("127.0.0.1",6379);
		je.auth("kone");
		je.set("age", "21");
		System.out.println(je.get("age"));
		
		Template tp = new Template();
		tp.setTempName("tempName");
		tp.setId(15);
		jedis.set("tp".getBytes(), serialize(tp));
		
        byte[] byt=jedis.get("tp".getBytes());
        Object obj=unserizlize(byt);
        if(obj instanceof Template){
            System.out.println(((Template) obj).getTempName());
        }
        
        User u = new User();
        u.setPassword("password");
        Teacher t = new Teacher();
        t.setName("kone");
        u.setTeacher(t);
//        jedis.setex("time", 10, "10s");
        System.out.println(jedis.get("time"));
        jedis.set("u".getBytes(), serialize(u));
        byte[] by = jedis.get("u".getBytes());
        Object oo = unserizlize(by);
        if(oo instanceof User) {
        	System.out.println(((User)oo).getPassword()+((User)oo).getTeacher().getName());
        }
        
        List<User> users = new ArrayList<User>();
        users.add(u);
        u.setPassword("passwo2");
        users.add(u);
        RedisTool.setRedis("uu", 10, users);
        Object uu = RedisTool.getReids("uu");
        System.out.println(((List<User>)uu).get(0).getPassword()+"----------"+((List<User>)uu).get(1).getPassword());
        
	}
	
	//序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        
        return null;
    }
}
