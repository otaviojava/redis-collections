package redis.clients.collections;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.CountStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.util.RedisConnection;

public class CountStructureTest {

	
	private CountStructure<Long> countStructure;
	
	private CountStructure<Double> countFluctuateStructure;
	
	@Before
	public void init() {
		
		countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("count_pages").withKey("products").buildLong();
		countFluctuateStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("currrency_value").withKey("dolar").buildDouble();
	}
	
	@Test
	public void shouldIncrementAndDecrementCount() {
		Long count = countStructure.increment();
		Assert.assertEquals(count, Long.valueOf(1L));
		count = countStructure.increment(10L);
		Assert.assertEquals(count, Long.valueOf(11L));
		
		count = countStructure.decrement();
		Assert.assertEquals(count, Long.valueOf(10L));
		count = countStructure.decrement(8L);
		Assert.assertEquals(count, Long.valueOf(2L));
	}
	
	@Test
	public void shouldDeleteCount() {
		Long count = 0L;
		countStructure.increment();
		countStructure.delete();
		Assert.assertEquals(count, countStructure.get());
	
	}
	
	@Test
	public void shouldIncrementAndDecrementCountFluctuate() {
		Double count = countFluctuateStructure.increment();
		Assert.assertEquals(count, Double.valueOf(1L));
		count = countFluctuateStructure.increment(10.2D);
		Assert.assertEquals(count, Double.valueOf(11.2D));
		
		count = countFluctuateStructure.decrement();
		Assert.assertEquals(count, Double.valueOf(10.2));
		count = countFluctuateStructure.decrement(8.4D);
		Assert.assertEquals(count, Double.valueOf(1.8));
	}
	
	@Test
	public void shouldDeleteCountFluctuate() {
		Long count = 0L;
		countStructure.increment();
		countStructure.delete();
		Assert.assertEquals(count, countStructure.get());
	
	}
	
	@AfterClass
	public static void dispose() {
		CountStructure<Long> countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("count_pages").withKey("products").buildLong();
		countStructure.delete();
		CountStructure<Double> countStructureDouble = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS).withNameSpace("currrency_value").withKey("dolar").buildDouble();
		countStructureDouble.delete();
	}
}
