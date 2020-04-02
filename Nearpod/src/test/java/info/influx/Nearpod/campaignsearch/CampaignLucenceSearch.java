package info.influx.Nearpod.campaignsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import info.influx.Nearpod.model.campaign.Campaign;
import info.influx.Nearpod.model.campaign.CampaignFilter;
import info.influx.Nearpod.repository.CampaignRepositoryEntityManagedImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CampaignLucenceSearch {
	
	@Autowired
	private CampaignRepositoryEntityManagedImpl campaignRepository;
	
	private List<Campaign> campaigns;
	
	@Before
	public void setupTestData() {
		campaigns = campaignRepository.dummyCampaignData();
	}
	
	@Test
	public void testA_whenInitialTestDataInserted_thenSuccess() {
		campaignRepository.createDummyCampaign();
	}
	
	@Test
	public void testB_whenIndexInitialized_thenCorrectIndexSize() throws InterruptedException{
		int indexSize = campaignRepository.createIndex();
		
		assertEquals(campaigns.size()-1, indexSize);
	}
	
	private CampaignFilter createCampaignTestFilter() {
		CampaignFilter filter = new CampaignFilter();
		filter.setSortBy("name");
		filter.setSortByOrder("asc");
		
		return filter;
	}
	
	@Test
	public void testC_whenKeywordSearchOnName_thenCorrectMatches() {
		List<Campaign> expected = campaigns;
		List<Campaign> results = campaignRepository.searchByCampaignData(createCampaignTestFilter());
		
		assertThat(results, containsInAnyOrder(expected.toArray()));
	}
}
