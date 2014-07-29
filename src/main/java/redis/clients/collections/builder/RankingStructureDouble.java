package redis.clients.collections.builder;

import redis.clients.collections.RankingStructure;
import redis.clients.collections.ScoresPoint;
import redis.clients.jedis.Jedis;


class RankingStructureDouble extends AbstractExpirable<Double> implements RankingStructure<Double> {

	RankingStructureDouble(Jedis jedis, String nameSpace) {
		super(jedis, Double.class, nameSpace);
	}

	@Override
	public ScoresPoint<Double> create(String key) {
		return new ScorePointDouble(jedis, RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

	@Override
	public void delete(String key) {
		jedis.del(RedisUtils.createKeyWithNameSpace(key, nameSpace));
	}

}
