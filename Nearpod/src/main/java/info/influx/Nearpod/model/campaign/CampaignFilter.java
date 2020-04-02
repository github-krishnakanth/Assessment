package info.influx.Nearpod.model.campaign;

import java.time.LocalDateTime;

import info.influx.Nearpod.model.user.TokenPayLoadInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Campaign Filter", description = "API Info used to provide filter's for campaign.")
public class CampaignFilter {
	@ApiModelProperty(notes = "Mode of campaign Ex: All, Completed, Ongoing, Historical")
	private String campaignStatus;
	@ApiModelProperty(notes = "Search based on Campaign Name")
	private String campaignSearchKeyWord;
	@ApiModelProperty(notes = "Location of Campaing Ex: Delhi, Selangore, Johar")
	private String campaignLocation;
	@ApiModelProperty(notes = "Date of Campaign Occur Ex: NextMonth")
	private LocalDateTime campaignDate;
	@ApiModelProperty(notes = "Field which to be sorted")
	private String sortBy;
	@ApiModelProperty(notes = "Campaing Field Order of Sorting")
	private String sortByOrder;
	@ApiModelProperty(notes = "User Information and Token Id for Authentication")
	private TokenPayLoadInfo tokenPayLoadInfo;
	@ApiModelProperty(notes = "Type of Field Ex: String, Number, Date")
	private String sortFieldType;
	@ApiModelProperty(notes = "Campaign Records Page Number Ex: Starts from 1")
	private int startPage;
	@ApiModelProperty(notes = "Number of Campaign Records to Display")
	private int totalPages;

	public String getCampaignStatus() {
		return campaignStatus;
	}
	public String getCampaignSearchKeyWord() {
		return campaignSearchKeyWord;
	}
	public String getCampaignLocation() {
		return campaignLocation;
	}
	public LocalDateTime getCampaignDate() {
		return campaignDate;
	}
	public String getSortBy() {
		return sortBy;
	}
	public String getSortByOrder() {
		return sortByOrder;
	}
	public TokenPayLoadInfo getTokenPayLoadInfo() {
		return tokenPayLoadInfo;
	}
	public String getSortFieldType() {
		return sortFieldType;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}
}
