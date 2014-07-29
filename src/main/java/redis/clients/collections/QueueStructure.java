package redis.clients.collections;

import java.util.Queue;

public interface QueueStructure <T> extends Expirable {


	Queue<T> get(String key);
	
	void delete(String key);

}
