package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.ListStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConnection;

public class ListBuilderTest {

	
	@Test
	public void shouldCreateListStructure() {
		ListStructure<Person> personStructure =  RedisStrutureBuilder.ofList(RedisConnection.JEDIS, Person.class).withNameSpace("nameSpaceList").build();
		Assert.assertNotNull(personStructure);
	}
	
	@Test(expected = RedisBuilderException.class)
	public void shouldNotCreateListStructure() {
		@SuppressWarnings("unused")
		ListStructure<Person> personStructure =  RedisStrutureBuilder.ofList(RedisConnection.JEDIS, Person.class).build();
	}
}
