package redis.clients.collections;

public interface Expirable {

	void expire(String key, int ttlSeconds);
	
	void persist(String key);
}
