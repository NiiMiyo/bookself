package douglas.bookself.models;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user")
	private Collection<BookRegister> registers;

	public Account() { super(); }
	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }

	public String getUsername() { return this.username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return this.password; }
	public void setPassword(String password) { this.password = password; }

	public Collection<BookRegister> getRegisters() { return registers; }
	public void setRegisters(Collection<BookRegister> registers) { this.registers = registers; }
}
