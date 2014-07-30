package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.CountStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.util.RedisConnection;

@SuppressWarnings("unused")
public class CountStructureBuilderTest {

	@Test
	public void shouldReturnCountStrutureBuilder() {
		CountStructure<Long> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("countSample").withKey("countKey").buildLong();
		Assert.assertNotNull(countStructure);
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldReturnErrorWhenNameSpaceIsNotInformed() {
		CountStructure<Long> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withKey("countKey").buildLong();
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldReturnErrorWhenKeyIsNotInformed() {
		CountStructure<Long> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withKey("countKey").withKey("countKey").buildLong();
	}
	
	@Test
	public void shouldReturnCountFluctuateStrutureBuilder() {
		CountStructure<Double> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("countSample").withKey("countKey").buildDouble();
		Assert.assertNotNull(countStructure);
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldReturnErrorWhenNameSpaceIsNotInformedFluctuate() {
		CountStructure<Double> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withKey("countKey").buildDouble();
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldReturnErrorWhenKeyIsNotInformedFluctuate() {
		CountStructure<Double> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withKey("countKey").withKey("countKey").buildDouble();
	}
}
