package info.influx.Nearpod.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import info.influx.Nearpod.model.campaign.CampaignFilter;
import info.influx.Nearpod.model.campaign.CampaignInfo;
import info.influx.Nearpod.repository.CampaignRepositoryEntityManagedImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static info.influx.provider.DeferredResultProvider.createDeferredResult; 
import static info.influx.Nearpod.service.impl.campaign.CampaignFacade.searchByCampaignData;

@Api(value = "documentController", description = "controller has all the document related api's")
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/campaign")
public class CampaignController {
	
	@Autowired
	private CampaignRepositoryEntityManagedImpl managed;
	
	@PostConstruct
	public void init() throws InterruptedException {
		managed.createDummyCampaign();
		managed.createIndex();
	}
	
	@ApiOperation(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			value = "Search Campaign",
			notes = "Search Campaign"
			)
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
		@ApiResponse(code = HttpServletResponse.SC_FOUND, message = "Data Found"),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Data not found"),
		@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid Request"),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error")
	})
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<List<CampaignInfo>>> search(
		@ApiParam(value = "Sort Order", name = "sortOrder")
		@RequestBody final CampaignFilter campaignFilter){
		return createDeferredResult(searchByCampaignData(campaignFilter).with(managed), HttpStatus.OK);
	}
}
