package redis.clients.collections;

import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.SetStructure;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.User;
import redis.clients.collections.util.RedisConnection;

public class SetStructureTest {

	private SetStructure<User> socialMediaUsers;
	private User userOtavioJava = new User("otaviojava");
	private User felipe = new User("ffrancesquini");
	
	@Before
	public void init() {
		socialMediaUsers = RedisStrutureBuilder.ofSet(RedisConnection.JEDIS, User.class).withNameSpace("socialMedia").build();
	}
	
	@Test
	public void shouldAddUsers() {
		Set<User> users = socialMediaUsers.createSet("twitter");
		Assert.assertTrue(users.isEmpty());
		users.add(userOtavioJava);
		Assert.assertTrue(users.size() == 1);
		
		users.remove(userOtavioJava);
		Assert.assertTrue(users.isEmpty());
	}
	@SuppressWarnings("unused")
	@Test
	public void shouldIterate() {
		Set<User> users = socialMediaUsers.createSet("twitter");
		
		users.add(userOtavioJava);
		users.add(userOtavioJava);
		users.add(felipe);
		users.add(userOtavioJava);
		users.add(felipe);
		int count = 0;
		for (User user: users) {
			count++;
		}
		Assert.assertTrue(count == 2);
		users.remove(userOtavioJava);
		users.remove(felipe);
		count = 0;
		for (User user: users) {
			count++;
		}
		Assert.assertTrue(count == 0);
	}
	
	@After
	public void dispose() {
		socialMediaUsers.delete("twitter");
	}
	
}
