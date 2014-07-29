package redis.clients.collections.builder;

import junit.framework.Assert;

import org.junit.Test;

import redis.clients.collections.SetStructure;
import redis.clients.collections.builder.RedisBuilderException;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConection;

public class SetBuilderStructureTest {

	
	@Test
	public void shouldCreateListStructure() {
		SetStructure<Person> personStructure =  RedisStrutureBuilder.ofSet(RedisConection.JEDIS, Person.class).withNameSpace("nameSpaceList").build();
		Assert.assertNotNull(personStructure);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = RedisBuilderException.class)
	public void shouldNotCreateListStructure() {
		SetStructure<Person> personStructure =  RedisStrutureBuilder.ofSet(RedisConection.JEDIS, Person.class).build();
	}
}
