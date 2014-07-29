package redis.clients.collections.model;

public class LineBank {

	
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public LineBank(String name, Integer age) {
		this.person = new Person(name, age);
	}
	
	
}
