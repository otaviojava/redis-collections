package redis.clients.collections;

import java.util.NoSuchElementException;
import java.util.Queue;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.QueueStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.LineBank;
import redis.clients.collections.util.RedisConnection;

public class QueueStructureTest {

	private QueueStructure<LineBank> serviceBank;
	
	@Before
	public void init() {
		serviceBank =  RedisStrutureBuilder.ofQueue(RedisConnection.JEDIS, LineBank.class).withNameSpace("serviceBank").build();
	}
	
	@Test
	public void shouldPushInTheLine() {
		Queue<LineBank> lineBank = serviceBank.get("createAccount");
		Assert.assertTrue(lineBank.add(new LineBank("Otavio", 25)));
		Assert.assertTrue(lineBank.size() ==1);
		LineBank otavio = lineBank.poll();
		Assert.assertEquals(otavio.getPerson().getName(), "Otavio");
		Assert.assertNull(lineBank.poll());
		Assert.assertTrue(lineBank.isEmpty());
	}
	
	@Test
	public void shouldPeekInTheLine() {
		Queue<LineBank> lineBank = serviceBank.get("createAccount");
		lineBank.add(new LineBank("Otavio", 25));
		LineBank otavio = lineBank.peek();
		Assert.assertNotNull(otavio);
		Assert.assertNotNull(lineBank.peek());
		LineBank otavio2 = lineBank.remove();
		Assert.assertEquals(otavio.getPerson().getName(), otavio2.getPerson().getName());
		boolean happendException = false;
		try {
			lineBank.remove();
		}catch(NoSuchElementException e) {
			happendException = true;
		}
		Assert.assertTrue(happendException);
	}
	
	@Test
	public void shouldElementInTheLine() {
		Queue<LineBank> lineBank = serviceBank.get("createAccount");
		lineBank.add(new LineBank("Otavio", 25));
		Assert.assertNotNull(lineBank.element());
		Assert.assertNotNull(lineBank.element());
		lineBank.remove(new LineBank("Otavio", 25));
		boolean happendException = false;
		try {
			lineBank.element();
		}catch(NoSuchElementException e) {
			happendException = true;
		}
		Assert.assertTrue(happendException);
	}
	@SuppressWarnings("unused")
	@Test
	public void shouldIterate() {
		Queue<LineBank> lineBank = serviceBank.get("createAccount");
		lineBank.add(new LineBank("Otavio", 25));
		lineBank.add(new LineBank("Gama", 26));
		int count = 0;
		for (LineBank line: lineBank) {
			count++;
		}
		Assert.assertTrue(count == 2);
		lineBank.remove();
		lineBank.remove();
		count = 0;
		for (LineBank line: lineBank) {
			count++;
		}
		Assert.assertTrue(count == 0);
	}
	@After
	public void dispose() {
		serviceBank.delete("createAccount");
	}
	
}

