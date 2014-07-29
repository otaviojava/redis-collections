package redis.clients.collections.builder;

public class RedisBuilderException extends RuntimeException {

	private static final long serialVersionUID = 1143453384777720845L;

	RedisBuilderException(String message){
		super(message);
	}
}
