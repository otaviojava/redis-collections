package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.MapStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConection;

public class MapBuilderStructureTest {

	
	@Test
	public void shouldCreateMapStructure() {
		MapStructure<Person> personStructure =  RedisStrutureBuilder.ofMap(RedisConection.JEDIS, Person.class).withNameSpace("nameSpaceList").build();
		Assert.assertNotNull(personStructure);
	}
	@SuppressWarnings("unused")
	@Test(expected = RedisBuilderException.class)
	public void shouldNotCreateMapStructureWhenNameSpaceIsNotInformed() {
		MapStructure<Person> personStructure =  RedisStrutureBuilder.ofMap(RedisConection.JEDIS, Person.class).build();
	}
	
}
