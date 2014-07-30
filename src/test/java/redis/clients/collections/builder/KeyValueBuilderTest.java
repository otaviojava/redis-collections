package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.keyValueRedisStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConnection;

public class KeyValueBuilderTest {

	@Test
	public void shouldCreateKeyValueStruture() {
		keyValueRedisStructure<Person> personStructure =  RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class).withNameSpace("person").withttl(120).build();
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldReturnErrorWhenNameSpaceWasNotInformed() {
		keyValueRedisStructure<Person> personStructure =  RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class).withttl(120).build();
		Assert.assertNotNull(personStructure);
	}
}
