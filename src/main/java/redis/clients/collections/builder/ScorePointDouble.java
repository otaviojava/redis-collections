package redis.clients.collections.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.collections.Ranking;
import redis.clients.collections.ScoresPoint;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

class ScorePointDouble extends AbstractScoresPoint<Double> implements ScoresPoint<Double> {


	ScorePointDouble(Jedis jedis, String keyWithNameSpace) {
		super(jedis, keyWithNameSpace);
	}

	@Override
	public void initialPoint(String field, Double value) {
		jedis.zadd(keyWithNameSpace, value, field);

	}

	@Override
	public Double increment(String field, Double value) {
		return jedis.zincrby(keyWithNameSpace, value, field);
	}
	@Override
	public Double decrement(String field, Double value) {
		return increment(field, -value);
	}

	@Override
	public List<Ranking<Double>> range(long start, long end) {
		List<Ranking<Double>> topRanking = new ArrayList<>();
		Set<Tuple> scores = jedis.zrevrangeWithScores(keyWithNameSpace, start, end);
		for (Tuple tuple: scores) {
			topRanking.add(new RankingDouble(tuple.getElement(),tuple.getScore()));
		}
		return topRanking;
	}

	@Override
	public String toString() {
		return "Aondde?: br.com.elo7.elodum.redis.builder.ScorePointDouble at " + keyWithNameSpace;
	}

}
