package redis.clients.collections.builder;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.RankingStructure;
import redis.clients.jedis.Jedis;

public class RankingStructureBuilder {

	private String nameSpace;
	
	private Jedis jedis;

	RankingStructureBuilder() {
		
	}

	RankingStructureBuilder(Jedis jedis) {
		this.jedis = jedis;
	}

	public RankingStructureBuilder withNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}

	public RankingStructure<Long> buildLong() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("The nameSpace must be specified");
		}
		return new RankingStructureLong(jedis, nameSpace);
	}
	
	public RankingStructure<Double> buildDouble() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("Oxii: The nameSpace must be specified");
		}
		return new RankingStructureDouble(jedis, nameSpace);
	}
}
