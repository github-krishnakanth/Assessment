package info.influx.Nearpod.model.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import info.influx.Nearpod.common.Builder;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	@Column(name = "role", nullable = false)
	private String role;
	@Column(name = "created_time", insertable = true, updatable = false)
	private LocalDateTime createdTime;
	@Column(name = "updated_time", insertable = false, updatable = true)
	private LocalDateTime updatedTime;
	
	private static Builder<User> builder(){
		return Builder.of(User.class);
	}
	
	@PrePersist
	protected void onCreate() {
		createdTime = LocalDateTime.now();
		updatedTime = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void  onUpdate() {
		updatedTime = LocalDateTime.now();
	}
}
