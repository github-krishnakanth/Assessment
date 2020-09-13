package solutions.info.influx.pojo;

/*import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;*/

//@ApiModel(description = "Details about the Response")
public class Response {
	//@ApiModelProperty(notes = "It tells about Operation Success or Failed")
	private boolean isSuccess;
	//@ApiModelProperty(notes = "It tells about status of the Operation")
	private String status;

	public Response(boolean isSuccess, String status) {
		this.isSuccess = isSuccess;
		this.status = status;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
