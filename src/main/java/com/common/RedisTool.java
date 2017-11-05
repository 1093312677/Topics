package com.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;
/**
 * Jedis缓存工具
 * @author kone
 * 2017.7.29
 */
public class RedisTool {
	private static Jedis jedis = null;
	public RedisTool() {
		jedis = new Jedis("127.0.0.1",6379);
//		jedis.auth("kone");
	}
	
	public static void init() {
		try{
			jedis = new Jedis("127.0.0.1",6379);
//			jedis.auth("kone");
		}catch(Exception e) {
			jedis = null;
		}
	}
	
	public static boolean setRedis(String key,int seconds, Object obj) {
		init();
		if(jedis == null) {
			return false;
		}
		try{
			jedis.setex(key.getBytes(),seconds, serialize(obj));
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public static Object getReids(String key) {
		init();
		if(jedis == null) {
			return null;
		}
		try{
			if(key != null){
				if(jedis.exists(key.getBytes())) {
					byte[] byt=jedis.get(key.getBytes());
					if(byt.length == 0) {
						return null;
					}
					Object obj=unserizlize(byt);
					return obj;
				}

			}
		} catch (Exception e) {
			return null;
		}

		return null;
		
	}
	
	/**
	 * 序列化 
	 * @param obj
	 * @return
	 */
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
    
    /**
     * 反序列化
     * @param byt
     * @return
     */
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
