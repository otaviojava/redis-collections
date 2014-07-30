package redis.clients.collections;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.collections.Ranking;
import redis.clients.collections.RankingStructure;
import redis.clients.collections.ScoresPoint;
import redis.clients.collections.builder.RedisStrutureBuilder;
import redis.clients.collections.model.ChampionShip;
import redis.clients.collections.model.TeamSoccer;
import redis.clients.collections.util.RedisConnection;

public class RankingStructureLongTest {

	private RankingStructure<Long> fifa;
	
	private ChampionShip roles;
	
	private TeamSoccer bahia = new TeamSoccer("Bahia");
	private TeamSoccer vitoria = new TeamSoccer("Vitoria");
	private TeamSoccer corinthians = new TeamSoccer("Corinthians");
	private TeamSoccer fluminense = new TeamSoccer("Fluminense");
	private TeamSoccer palmeiras = new TeamSoccer("Palmeiras");
	
	private List<TeamSoccer> firstDivision;
	
	private ScoresPoint<Long> brazilianChampionShip;
	
	@Before
	public void init() {
		fifa = RedisStrutureBuilder.ofRanking(RedisConnection.JEDIS).withNameSpace("fifa").buildLong();
		firstDivision = Arrays.asList(corinthians, bahia, vitoria, palmeiras);
		roles = new ChampionShip();
		brazilianChampionShip = fifa.create("brazilianChampionShip");
	}
	
	@Test
	public void shouldStartChampionShip() {

		for(TeamSoccer soccerTeam: firstDivision) {
			brazilianChampionShip.increment(soccerTeam.getName(), 0L);
		}

		Assert.assertTrue(brazilianChampionShip.size() == 4);
		for(Ranking<Long> ranking: brazilianChampionShip.getRanking()) {
			Assert.assertNotNull(ranking);
		}

	}
	
	@Test
	public void shouldAccpedTapetaoPowerStartFirstGame() {
		roles.play(fluminense, vitoria, brazilianChampionShip);
		Assert.assertEquals(brazilianChampionShip.top(1).get(0).getName(),fluminense.getName());
		Assert.assertEquals(brazilianChampionShip.top(1).get(0).getPoints(),Long.valueOf(3L));
	}
	
	@Test
	public void palmeirasNeverWin() {
		roles.play(palmeiras, palmeiras, brazilianChampionShip);
		Ranking<Long> palmeirasRanking = brazilianChampionShip.top(1).get(0);
		Assert.assertEquals(palmeirasRanking.getPoints(), Long.valueOf(3L));
		Assert.assertEquals(palmeirasRanking.getName(), palmeiras.getName());
		
		brazilianChampionShip.decrement(palmeiras.getName(), 3L);
		for(Ranking<Long> ranking: brazilianChampionShip.getRanking()) {
			if (ranking.getName().equals(palmeiras.getName())) {
				if(ranking.getPoints() !=0) {
					throw new RuntimeException("Palmeiras should have not scored points");	
				}
			}
		}
	}
	
	@Test
	public void shouldRemovesLastOne() {
		for(TeamSoccer soccerTeam: firstDivision) {
			brazilianChampionShip.increment(soccerTeam.getName(), 0L);
		}
		usingTapetao();
		
		executeChampionship();
		
		for(Ranking<Long> ranking: brazilianChampionShip.getRanking()) {
			Assert.assertNotNull(ranking);
		}
		
		Ranking<Long> winner = brazilianChampionShip.top(1).get(0);
		Assert.assertEquals(winner.getName(), bahia.getName());
		
		List<Ranking<Long>> rebaixamentoTeam = brazilianChampionShip.last(2);
		Assert.assertEquals(rebaixamentoTeam.get(0).getName(), vitoria.getName());
		Assert.assertEquals(rebaixamentoTeam.get(1).getName(), palmeiras.getName());
		
		brazilianChampionShip.remove(rebaixamentoTeam.get(0).getName());
		brazilianChampionShip.remove(rebaixamentoTeam.get(1).getName());
		
		for(Ranking<Long> ranking: brazilianChampionShip.getRanking()) {
			if(ranking.getName().equals("Palmeiras") || ranking.getName().equals("Vitoria")) {
				throw new RuntimeException("These times cannot stay here because they gonna to division B next year!!!");
			}
		}
		
	}

	private void executeChampionship() {
		roles.play(fluminense, vitoria, brazilianChampionShip);
		roles.play(fluminense, bahia, brazilianChampionShip);
		roles.play(fluminense, corinthians, brazilianChampionShip);
		roles.play(fluminense, palmeiras, brazilianChampionShip);
		
		roles.play(vitoria, bahia, brazilianChampionShip);
		roles.play(vitoria, corinthians, brazilianChampionShip);
		roles.play(vitoria, palmeiras, brazilianChampionShip);
		
		roles.play(bahia, corinthians, brazilianChampionShip);
		roles.play(bahia, palmeiras, brazilianChampionShip);
		
		roles.play(corinthians, palmeiras, brazilianChampionShip);
	}

	private void usingTapetao() {
		brazilianChampionShip.increment(fluminense.getName(), 0L);
	}
	
	@After
	public void dispose() {
		fifa.delete("brazilianChampionShip");
	}
}
