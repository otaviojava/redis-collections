package redis.clients.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.MapStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.Species;
import redis.clients.collections.util.RedisConection;

public class MapStructureTest {

	private MapStructure<Species> zoo;
	
	private Species mammals = new Species("lion", "cow", "dog");
	private Species fishes = new Species("redfish", "glassfish");
	private Species amphibians = new Species("crododile", "frog");
	
	@Before
	public void init() {
		zoo = RedisStrutureBuilder.ofMap(RedisConection.JEDIS, Species.class).withNameSpace("animalZoo").build();
	}
	
	@Test
	public void shouldPutAndGetMap() {
		Map<String, Species> vertebrates = zoo.get("vertebrates");
		Assert.assertTrue(vertebrates.isEmpty());
		Assert.assertNotNull(vertebrates.put("mammals", mammals));
		Species species = vertebrates.get("mammals");
		Assert.assertNotNull(species);
		Assert.assertEquals(species.getAnimals().get(0), mammals.getAnimals().get(0));
		Assert.assertTrue(vertebrates.size() == 1);
	}
	
	@Test
	public void shouldVerifyExist() {
		Map<String, Species> vertebrates = zoo.get("vertebrates");
		vertebrates.put("mammals", mammals);
		Assert.assertTrue(vertebrates.containsKey("mammals"));
		Assert.assertFalse(vertebrates.containsKey("redfish"));
		
		Assert.assertTrue(vertebrates.containsValue(mammals));
		Assert.assertFalse(vertebrates.containsValue(fishes));
	}
	
	@Test
	public void shouldShowKeyAndValues() {
		Map<String, Species> vertebratesMap = new HashMap<>();
		vertebratesMap.put("mammals", mammals);
		vertebratesMap.put("fishes", fishes);
		vertebratesMap.put("amphibians", amphibians);
		Map<String, Species> vertebrates = zoo.get("vertebrates");
		vertebrates.putAll(vertebratesMap);
		
		Set<String> keys = vertebrates.keySet();
		Collection<Species> collectionSpecies = vertebrates.values();
		
		Assert.assertTrue(keys.size() == 3);
		Assert.assertTrue(collectionSpecies.size() == 3);
		Assert.assertNotNull(vertebrates.remove("mammals"));
		Assert.assertNull(vertebrates.remove("mammals"));
		Assert.assertNull(vertebrates.get("mammals"));
		Assert.assertTrue(vertebrates.size() == 2);
	}
	
	@After
	public void dispose() {
		zoo.delete("vertebrates");
	}
	
}
