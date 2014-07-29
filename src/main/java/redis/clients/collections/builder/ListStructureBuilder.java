package redis.clients.collections.builder;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.ListStructure;
import redis.clients.jedis.Jedis;

public class ListStructureBuilder<T> {

	private Class<T> clazz;
	
	private String nameSpace;
	
	private Jedis jedis;
	
	ListStructureBuilder(Jedis jedis, Class<T> clazz) {
		this.clazz = clazz;
		this.jedis = jedis;
	}

	public ListStructureBuilder<T> withNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}
	
	public ListStructure<T> build() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("Oxii: The nameSpace must be specified");
		}
		return new ListStructureImpl<>(jedis, clazz, nameSpace);
	}
}
