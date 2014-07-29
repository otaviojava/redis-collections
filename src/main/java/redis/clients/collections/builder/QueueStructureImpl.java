package redis.clients.collections.builder;

import java.util.Objects;
import java.util.Queue;

import redis.clients.collections.QueueStructure;
import redis.clients.jedis.Jedis;

class QueueStructureImpl<T> extends AbstractExpirable<T> implements QueueStructure<T> {

	

	QueueStructureImpl(Jedis jedis, Class<T> clazz, String nameSpace) {
		super(jedis, clazz, nameSpace);
	}

	@Override
	public Queue<T> get(String key) {
		Objects.requireNonNull(key);
		return new RedisQueue<>(jedis, clazz, RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

	@Override
	public void delete(String key) {
		jedis.del(RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}
	

}
