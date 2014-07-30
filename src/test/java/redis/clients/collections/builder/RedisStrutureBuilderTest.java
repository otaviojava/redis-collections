package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.builder.CountStructureBuilder;
import redis.clients.collections.builder.KeyValueBuilder;
import redis.clients.collections.builder.ListStructureBuilder;
import redis.clients.collections.builder.MapStructureBuilder;
import redis.clients.collections.builder.QueueStructureBuilder;
import redis.clients.collections.builder.RankingStructureBuilder;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.builder.SetStructureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConnection;

@SuppressWarnings("unused")
public class RedisStrutureBuilderTest {


	@Test
	public void shouldCreateKeyValueStrutureBuilder() {
		KeyValueBuilder<Person> personStructure =  RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class);
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldRetornErroWhenCreatedKeyKeyValueStructureBuilder() {
		KeyValueBuilder<Person> personStructure =  RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, null);
	}
	
	@Test
	public void shouldCreateCountStruture() {
		CountStructureBuilder countStructure = RedisStrutureBuilder.ofCount(RedisConnection.JEDIS);
		Assert.assertNotNull(countStructure);
	}
	
	@Test
	public void shouldCreateRankingStruture() {
		RankingStructureBuilder countStructure = RedisStrutureBuilder.ofRanking(RedisConnection.JEDIS);
		Assert.assertNotNull(countStructure);
	}
	
	@Test
	public void shouldCreateListStrutureBuilder() {
		ListStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofList(RedisConnection.JEDIS, Person.class);
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldRetornErroWhenCreatedListStructureBuilder() {
		ListStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofList(RedisConnection.JEDIS, null);
	}
	
	@Test
	public void shouldCreateSetStrutureBuilder() {
		SetStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofSet(RedisConnection.JEDIS, Person.class);
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldRetornErroWhenCreatedSetStructureBuilder() {
		SetStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofSet(RedisConnection.JEDIS, null);
	}
	
	@Test
	public void shouldCreateMapStrutureBuilder() {
		MapStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofMap(RedisConnection.JEDIS, Person.class);
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldRetornErroWhenCreatedMapStructureBuilder() {
		MapStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofMap(RedisConnection.JEDIS, null);
	}
	
	@Test
	public void shouldCreateQueueStrutureBuilder() {
		QueueStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofQueue(RedisConnection.JEDIS, Person.class);
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldRetornErroWhenCreatedQueueStructureBuilder() {
		QueueStructureBuilder<Person> personStructure =  RedisStrutureBuilder.ofQueue(RedisConnection.JEDIS, null);
	}
	
	
}
