package redis.clients.collections;

import java.util.List;

public interface keyValueRedisStructure<T> {

	T get(String key);
	
	void set(String key, T bean);

	List<T> multiplesGet(Iterable<String> keys);

	void delete(String key);
}
