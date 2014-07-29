package redis.clients.collections.builder;

import redis.clients.collections.Ranking;

class RankingLong implements Ranking<Long> {

	private String name;

	private Long point;

	RankingLong(String key, String value) {
		this.name = key;
		this.point = Long.valueOf(value);
	}
	
	RankingLong(String key, Long point) {
		this.name = key;
		this.point = point;
	}

	@Override
	public Long getPoints() {
		return point;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "br.com.elo7.elodum.redis.builder.Ranking<Long> name: " + name + " point: " + point;
	}
}
