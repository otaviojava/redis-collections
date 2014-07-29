package redis.clients.collections.builder;

import redis.clients.collections.RankingStructure;
import redis.clients.collections.ScoresPoint;
import redis.clients.jedis.Jedis;


class RankingStructureLong extends AbstractExpirable<Long> implements RankingStructure<Long> {

	RankingStructureLong(Jedis jedis,String nameSpace) {
		super(jedis, Long.class, nameSpace);
	}

	@Override
	public ScoresPoint<Long> create(String key) {
		return new ScorePointLong(jedis, RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

	@Override
	public void delete(String key) {
		jedis.del(RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

}
