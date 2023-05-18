/*
 * 
 * 
 * @author: David Martínez Bergantiño 
 * */

package trivia;

import java.util.UUID;

public class Questions {
	private UUID id;
	private String value;
	
	public Questions(String value) {
		this.id = UUID.randomUUID();
		this.value = value;
	}
	
	protected UUID getId() {
		return this.id;
	}
	
	protected String getValue() {
		return this.value;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.value.hashCode();
	}
	
	public boolean equals(Questions q1) {
		return (this.id.equals(q1.getId()));
	}
	
}
