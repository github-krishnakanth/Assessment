package info.influx.Nearpod.repository;

import info.influx.Nearpod.model.campaign.Campaign;

public interface CampaignRepositoryEntityManaged {
	void refresh(Campaign campaign);
}
