package redis.clients.collections.model;

public class User {

	private String nickName;

	public User(String nickName) {
		this.setNickName(nickName);
		
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
