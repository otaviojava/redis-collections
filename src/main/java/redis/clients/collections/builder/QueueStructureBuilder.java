package redis.clients.collections.builder;

import org.apache.commons.lang3.StringUtils;

import redis.clients.collections.QueueStructure;
import redis.clients.jedis.Jedis;

public class QueueStructureBuilder<T> {

	private Class<T> clazz;
	
	private String nameSpace;

	private Jedis jedis;
	
	QueueStructureBuilder(Jedis jedis, Class<T> clazz) {
		this.jedis = jedis;
		this.clazz = clazz;
	}

	public QueueStructureBuilder<T> withNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}
	
	public QueueStructure<T> build() {
		if (StringUtils.isBlank(nameSpace)) {
			throw new RedisBuilderException("The nameSpace must be specified");
		}
		return new QueueStructureImpl<>(jedis, clazz, nameSpace);
	}
}
