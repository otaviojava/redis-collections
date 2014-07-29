package redis.clients.collections;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.Ranking;
import redis.clients.collections.RankingStructure;
import redis.clients.collections.ScoresPoint;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.util.RedisConection;

public class RankingStructureDoubleTest {

	private RankingStructure<Double> laughThings;
	
	
	@Before
	public void init() {
		laughThings = RedisStrutureBuilder.ofRanking(RedisConection.JEDIS).withNameSpace("laughThings").buildDouble();
	}
	
	@Test
	public void shouldAddAndRemoveValue() {
		ScoresPoint<Double> sneeze = laughThings.create("sneeze");
		
		sneeze.increment("Newton", 24.2424242424);
		sneeze.increment("Erish", 25.9);
		sneeze.increment("David", 924.786);
		
		List<Ranking<Double>> ranking = sneeze.getRanking();
		
		Assert.assertEquals(ranking.get(0).getName(), "David");
		Assert.assertEquals(ranking.get(1).getName(), "Erish");
		Assert.assertEquals(ranking.get(2).getName(), "Newton");
		
		Assert.assertEquals(ranking.get(0).getPoints(), Double.valueOf(924.786));
		Assert.assertEquals(ranking.get(1).getPoints(), Double.valueOf(25.9));
		Assert.assertEquals(ranking.get(2).getPoints(), Double.valueOf(24.2424242424));
		
	}
	@After
	public void dispose() {
		laughThings.delete("sneeze");
	}
}
