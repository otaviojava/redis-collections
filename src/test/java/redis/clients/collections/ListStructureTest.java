package redis.clients.collections;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.ListStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.ProductCart;
import redis.clients.collections.util.RedisConection;

public class ListStructureTest {

	private static final String FRUITS = "fruits";
	private ProductCart banana = new ProductCart("banana", BigDecimal.ONE);
	private ProductCart orange = new ProductCart("orange", BigDecimal.ONE);
	private ProductCart waterMelon = new ProductCart("waterMelon", BigDecimal.TEN);
	private ProductCart melon = new ProductCart("melon", BigDecimal.ONE);
	
	private ListStructure<ProductCart> shippingCart;
	
	
	@Before
	public void init() {
		shippingCart = RedisStrutureBuilder.ofList(RedisConection.JEDIS, ProductCart.class).withNameSpace("list_producs").build();
	}
	
	@Test
	public void shouldReturnsList() {
		List<ProductCart> frutisCarts =  shippingCart.get(FRUITS);
		Assert.assertNotNull(frutisCarts);
	}
	
	@Test
	public void shouldAddList() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);
		Assert.assertTrue(fruitsCarts.isEmpty());
		fruitsCarts.add(banana);
		Assert.assertFalse(fruitsCarts.isEmpty());
		ProductCart banana = fruitsCarts.get(0);
		Assert.assertNotNull(banana);
		Assert.assertEquals(banana.getName(), "banana");
	}
	
	@Test
	public void shouldSetList() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);

		fruitsCarts.add(banana);
		fruitsCarts.add(0, orange);
		Assert.assertTrue(fruitsCarts.size() == 2);
		
		Assert.assertEquals(fruitsCarts.get(0).getName(), "orange");
		Assert.assertEquals(fruitsCarts.get(1).getName(), "banana");
		
		fruitsCarts.set(0, waterMelon);
		Assert.assertEquals(fruitsCarts.get(0).getName(), "waterMelon");
		Assert.assertEquals(fruitsCarts.get(1).getName(), "banana");
		
	}
	
	@Test
	public void shouldRemoveList() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);
		fruitsCarts.add(banana);
	}
	
	@Test
	public void shouldReturnIndexOf() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);
		
		
		
		fruitsCarts.add(new ProductCart("orange", BigDecimal.ONE));
		fruitsCarts.add(banana);
		fruitsCarts.add(new ProductCart("watermellon", BigDecimal.ONE));
		fruitsCarts.add(banana);
		Assert.assertTrue(fruitsCarts.indexOf(banana) == 1);
		Assert.assertTrue(fruitsCarts.lastIndexOf(banana) == 3);
		
		Assert.assertTrue(fruitsCarts.contains(banana));
		Assert.assertTrue(fruitsCarts.indexOf(melon) == -1);
		Assert.assertTrue(fruitsCarts.lastIndexOf(melon) == -1);
	}
	
	@Test
	public void shouldReturnContains() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);
		
		fruitsCarts.add(orange);
		fruitsCarts.add(banana);
		fruitsCarts.add(waterMelon);
		Assert.assertTrue(fruitsCarts.contains(banana));
		Assert.assertFalse(fruitsCarts.contains(melon));
		Assert.assertTrue(fruitsCarts.containsAll(Arrays.asList(banana, orange)));
		Assert.assertFalse(fruitsCarts.containsAll(Arrays.asList(banana, melon)));
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void shouldIterate() {
		List<ProductCart> fruitsCarts =  shippingCart.get(FRUITS);
		fruitsCarts.add(melon);
		fruitsCarts.add(banana);
		int count = 0;
		for (ProductCart fruiCart: fruitsCarts) {
			count++;
		}
		Assert.assertTrue(count == 2);
		fruitsCarts.remove(0);
		fruitsCarts.remove(0);
		count = 0;
		for (ProductCart fruiCart: fruitsCarts) {
			count++;
		}
		Assert.assertTrue(count == 0);
	}
	@After
	public  void end() {
		shippingCart.delete(FRUITS);
	}
}
