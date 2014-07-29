package redis.clients.collections;

import java.util.Map;

public interface MapStructure <T> extends Expirable{


	Map<String, T> get(String key);
	
	void delete(String key);
	
}
