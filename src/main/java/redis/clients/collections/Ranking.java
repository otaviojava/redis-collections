package redis.clients.collections;

public interface Ranking<T extends Number> {

	T getPoints();
	
	String getName();
}
