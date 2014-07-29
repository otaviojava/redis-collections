package redis.clients.collections.builder;

import java.util.Set;

import redis.clients.collections.SetStructure;
import redis.clients.jedis.Jedis;

class SetStructureImpl<T> extends AbstractExpirable<T> implements SetStructure<T> {


	SetStructureImpl(Jedis jedis, Class<T> clazz, String nameSpace) {
		super(jedis, clazz, nameSpace);
	}
	
	@Override
	public Set<T> createSet(String key) {
		return new RedisSet<>(jedis, clazz, RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

	@Override
	public void delete(String key) {
		jedis.del(RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

}
