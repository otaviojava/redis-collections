package redis.clients.collections.builder;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.SetStructure;
import redis.clients.jedis.Jedis;

public class SetStructureBuilder<T> {

	private Class<T> clazz;
	
	private String nameSpace;

	private Jedis jedis;
	
	public SetStructureBuilder(Jedis jedis, Class<T> clazz) {
		this.jedis = jedis;
		this.clazz = clazz;
	}

	public SetStructureBuilder<T> withNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}
	
	public SetStructure<T> build() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("The nameSpace must be specified");
		}
		return new SetStructureImpl<>(jedis, clazz, nameSpace);
	}
}
