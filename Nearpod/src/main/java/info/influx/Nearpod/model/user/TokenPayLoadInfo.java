package info.influx.Nearpod.model.user;

import info.influx.Nearpod.common.Builder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Token Payload Transfer between UI")
@Data
public class TokenPayLoadInfo {
	@ApiModelProperty(notes = "Holds Username")
	private String username;
	@ApiModelProperty(notes = "Holds Password")
	private String password;
	@ApiModelProperty(notes = "Holds Token Id for Authentication")
	private String tokenId;
	@ApiModelProperty(notes = "Is Valid")
	private Boolean valid;
	@ApiModelProperty(notes = "Authentication Response Message")
	private String message;
	
	public static Builder<TokenPayLoadInfo> builder(){
		return Builder.of(TokenPayLoadInfo.class);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTokenId() {
		return tokenId;
	}

	public Boolean getValid() {
		return valid;
	}

	public String getMessage() {
		return message;
	}
}
