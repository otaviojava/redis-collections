package redis.clients.collections.util;

import redis.clients.jedis.Jedis;

public class RedisConection {

	public static final Jedis JEDIS;
	
	static{
	 JEDIS = new Jedis("localhost");
	}
}
