package info.influx.Nearpod.model.campaign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "Campaign Data", description = "API Data Model Provides Campaign Data.")
public class CampaignInfo {

	@ApiModelProperty(notes = "Name of Campaign")
    private String name;
    @ApiModelProperty(notes = "Duration of Campaign")
    private String duration;
    @ApiModelProperty(notes = "Status of Campaign ex: Processing, Completed, Published, Ongoing, etc..")
    private String status;
    @ApiModelProperty(notes = "Date of Campaign starts. Accepts Date")
    private LocalDateTime startDate;
    @ApiModelProperty(notes = "Date of Campaign end. Accepts Date")
    private LocalDateTime endDate;
    @ApiModelProperty(notes = "Provides the Report Status")
    private String report;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
}
