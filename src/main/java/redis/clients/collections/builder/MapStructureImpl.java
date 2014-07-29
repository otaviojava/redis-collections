package redis.clients.collections.builder;

import java.util.Map;

import redis.clients.collections.MapStructure;
import redis.clients.jedis.Jedis;

class MapStructureImpl<T> extends AbstractExpirable<T> implements MapStructure<T> {


	MapStructureImpl(Jedis jedis, String nameSpace, Class<T> clazz){
		super(jedis, clazz, nameSpace);
	}

	@Override
	public Map<String, T> get(String key) {
		return new RedisMap<T>(jedis, clazz, RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

	@Override
	public void delete(String key) {
		jedis.del(RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}
	

}
