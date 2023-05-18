/*
 * 
 * 
 * @author: David Martínez Bergantiño 
 * */

package trivia;

import java.util.UUID;

public class Answers {
	private Questions question;
	private UUID id;
	private String value;
	
	public Answers(String value, Questions question) {
		this.question = question;
		this.id = question.getId();
		this.value = value;	
	}
	
	protected UUID getId() {
		return this.id;
	}
	
	protected String getValue( ) {
		return value;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.value.hashCode() + this.question.hashCode();
	}
	
	public boolean equals(Questions q1) {
		return (this.id.equals(q1.getId()));
	}
}
