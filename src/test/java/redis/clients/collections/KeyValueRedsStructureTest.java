package redis.clients.collections;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.keyValueRedisStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Person;
import redis.clients.collections.util.RedisConnection;

public class KeyValueRedsStructureTest {
	
	private static final int TWO_SECONDS = 2;
	private static final String UNKNOWN_PERSON_KEY = "UNKNOWN_PERSON";
	private static final String OTAVIOJAVA_KEY = "otaviojava";
	private static final String OTAVIOJAVA_TEMP_KEY = "otaviojava_temp";
	private static final String GAMA_KEY = "gamaFominha";
	private static final String FRANCESQUINI_KEY = "guardanapoRecadinho";

	private keyValueRedisStructure<Person> personStructure;
	private keyValueRedisStructure<Person> personTemporaryStructure;
	
	
	
	@Before
	public void init() {
		personStructure = RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class).withNameSpace("person_key_value").build();
		personTemporaryStructure = RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class).withNameSpace("person_key_value").withttl(TWO_SECONDS).build();
	}
	
	@Test
	public void shouldReturnNullAndNotFindKey() {
		Person person = personStructure.get(UNKNOWN_PERSON_KEY);
		Assert.assertNull(person);
	}

	@Test
	public void shouldSetAndReturnPerson() {
		Person otavio = new Person("Otavio", 25);
		personStructure.set(OTAVIOJAVA_KEY, otavio);
		Person otavioRedis = personStructure.get(OTAVIOJAVA_KEY);
		Assert.assertNotNull(otavioRedis);
		Assert.assertEquals(otavio.getName(), otavioRedis.getName());
	}
	
	@Test
	public void shouldSetAndwaitAndNotReturnPerson() throws InterruptedException {
		Person otavio = new Person("Otavio", 25);
		personTemporaryStructure.set(OTAVIOJAVA_TEMP_KEY, otavio);
		Person otavioRedis = personTemporaryStructure.get(OTAVIOJAVA_TEMP_KEY);
		Assert.assertNotNull(otavioRedis);
		Assert.assertEquals(otavio.getName(), otavioRedis.getName());
		Thread.sleep(3000L);
		Assert.assertNull(personTemporaryStructure.get(OTAVIOJAVA_TEMP_KEY));
	}
	
	@Test
	public void shouldDelAndReturnPerson() {
		Person otavio = new Person("Otavio", 25);
		personStructure.set(OTAVIOJAVA_KEY, otavio);
		personStructure.delete(OTAVIOJAVA_KEY);
		Person otavioRedis = personStructure.get(OTAVIOJAVA_KEY);
		Assert.assertNull(otavioRedis);
	}
	
	@Test
	public void shouldGelAndReturMultiplePersons() {
		Person otavio = new Person("Otavio", 25);
		Person gama = new Person("Gama", 28);
		Person francesquini = new Person("Felie", 25);
		personStructure.set(OTAVIOJAVA_KEY, otavio);
		personStructure.set(GAMA_KEY, gama);
		personStructure.set(FRANCESQUINI_KEY, francesquini);
		
		List<Person> persons = personStructure.multiplesGet(Arrays.asList(OTAVIOJAVA_KEY, GAMA_KEY, FRANCESQUINI_KEY, UNKNOWN_PERSON_KEY));
		
		Assert.assertNotNull(persons);
		Assert.assertEquals(persons.size(), 3);
	}
	
	@AfterClass
	public static void dispose() {
		keyValueRedisStructure<Person> personStructure = RedisStrutureBuilder.ofKeyValue(RedisConnection.JEDIS, Person.class).withNameSpace("person_key_value").build();
		personStructure.delete(FRANCESQUINI_KEY);
		personStructure.delete(OTAVIOJAVA_KEY);
		personStructure.delete(GAMA_KEY);
	}
}
