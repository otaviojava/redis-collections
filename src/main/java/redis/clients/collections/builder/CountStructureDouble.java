package redis.clients.collections.builder;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.CountStructure;
import redis.clients.jedis.Jedis;

class CountStructureDouble implements CountStructure<Double> {

	private String keyWithNameSpace;
	
	private Jedis jedis;


	CountStructureDouble(Jedis jedis, String keyWithNameSpace) {
		this.keyWithNameSpace = keyWithNameSpace;
		this.jedis = jedis;
	}
	
	@Override
	public Double increment() {
		return increment(1D);
	}

	@Override
	public Double increment(Double count) {
		return jedis.incrByFloat(keyWithNameSpace, count);
	}

	@Override
	public Double decrement() {
		return increment(-1D);
	}

	@Override
	public Double decrement(Double count) {
		return increment(-count);
	}

	@Override
	public void delete() {
		jedis.del(keyWithNameSpace);
	}

	@Override
	public Double get() {
		String value = jedis.get(keyWithNameSpace);
		if (StringUtils.isNotBlank(value)) {
			return Double.valueOf(value);
		}
		return 0D;
	}

	@Override
	public void expires(int ttlSeconds) {
		jedis.ttl(keyWithNameSpace);
	}

	@Override
	public void persist() {
		jedis.persist(keyWithNameSpace);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(keyWithNameSpace);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (CountStructureDouble.class.isInstance(obj)) {
			CountStructureDouble otherRedis = CountStructureDouble.class.cast(obj);
			return Objects.equals(otherRedis.keyWithNameSpace, keyWithNameSpace);
		}
		return false;
	}

	@Override
	public String toString() {
		return "br.com.elo7.elodum.redis.builder.CountStruture<Double> at " + keyWithNameSpace;
	}
	
}
