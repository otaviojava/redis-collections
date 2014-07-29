package redis.clients.collections.model;

public class TeamSoccer {

	private String name;

	public TeamSoccer(String name) {
		this.setName(name);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
