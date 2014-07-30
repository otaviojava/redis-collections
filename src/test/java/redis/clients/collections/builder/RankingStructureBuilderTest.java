package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.RankingStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.util.RedisConnection;

public class RankingStructureBuilderTest {

	
	@Test
	public void shouldCreateRankingStructure() {
		RankingStructure<Long> structure = RedisStrutureBuilder.ofRanking(RedisConnection.JEDIS).withNameSpace("rankingSpace").buildLong();
		Assert.assertNotNull(structure);
	}
	@SuppressWarnings("unused")
	@Test(expected = RedisBuilderException.class)
	public void shouldNotCreateRankingStructure() {
		RankingStructure<Long> structure = RedisStrutureBuilder.ofRanking(RedisConnection.JEDIS).buildLong();
	}
}
