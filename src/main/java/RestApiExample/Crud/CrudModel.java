/**
 * 
 */
package RestApiExample.Crud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author khant
 *
 */
@Entity
@Table(name = "person")
public class CrudModel {

	private long personId;
	private String name;
	private String age;

	public CrudModel() {
	}

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "person_id")
	public long getPersonId() {
		return personId;
	}

	public CrudModel(long personId, String name, String age) {
		this.personId = personId;
		this.name = name;
		this.age = age;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
