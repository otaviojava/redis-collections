package redis.clients.collections.builder;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.keyValueRedisStructure;
import redis.clients.jedis.Jedis;


public class KeyValueBuilder<T> {

	private Class<T> clazz;

	private String nameSpace;
	
	private int ttl;
	
	private Jedis jedis;
	
	KeyValueBuilder(Jedis jedis, Class<T> clazz) {
		this.clazz = clazz;
		this.jedis = jedis;
	}

	public KeyValueBuilder<T> withNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}

	public KeyValueBuilder<T> withttl(int ttl) {
		this.ttl = ttl;
		return this;
	}

	public keyValueRedisStructure<T> build() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("The nameSpace should be informed");
		}
		return new keyValueRedisStructureImpl<>(jedis, clazz, nameSpace, ttl);
	}
	
}
