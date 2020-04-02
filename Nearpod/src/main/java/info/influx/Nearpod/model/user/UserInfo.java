package info.influx.Nearpod.model.user;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.swagger.annotations.ApiModelProperty;

public class UserInfo {
	static final long serialVersionId = 1L;
	
	@ApiModelProperty(notes = "The Database generated user, token and session mapping Id")
	private Long id;
	@ApiModelProperty(notes = "username")
	private String username;
	@ApiModelProperty(notes = "password")
	private String password;
	@ApiModelProperty(notes = "Role of user")
	private String role;
	@ApiModelProperty(notes = "Indicated whether the user is enabled or disabled. A disabled user cannot be authenticated")
	private boolean enabled;
	@ApiModelProperty(notes = "The Database generated user, token and session mapping created time")
	private LocalDateTime createdTime;
	@ApiModelProperty(notes = "The Database generated user, token and session mapping updated time")
	private LocalDateTime updatedTime;
		
	public UserInfo() {
	}

	public UserInfo(String username, String password, boolean enabled, LocalDateTime createdTime,
			LocalDateTime updatedTime) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
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

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
}
