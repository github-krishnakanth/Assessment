package info.influx.Nearpod.model.user;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_token_session")
@Data
public class UserTokenSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "token", nullable = false, unique = true, length = 500)
	private String token;
	@Column(name = "session_id", nullable = false, unique = true)
	private String sessionId;
	@Column(name = "expiry_time", nullable = false)
	private Long expiryTime;
	@Column(name = "created_time", insertable = true, updatable = false)
	private LocalDateTime createdTime;
	@Column(name = "updated_time", insertable = false, updatable = true)
	private LocalDateTime updatedTime;
	
	public UserTokenSession() {
	}

	public UserTokenSession(String username, String token, String sessionId, Long expiryTime) {
		this.username = username;
		this.token = token;
		this.sessionId = sessionId;
		this.expiryTime = expiryTime;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getToken() {
		return token;
	}

	public Long getExpiryTime() {
		return expiryTime;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	@PrePersist
	protected void onCreate() {
		createdTime = LocalDateTime.now();
		updatedTime = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedTime = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(username, token, sessionId, expiryTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTokenSession other = (UserTokenSession) obj;
		if (expiryTime == null) {
			if (other.expiryTime != null)
				return false;
		} else if (!expiryTime.equals(other.expiryTime))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserTokenSession [id=" + id + ", username=" + username + ", token=" + token + ", sessionId=" + sessionId
				+ ", expiryTime=" + expiryTime + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
}
