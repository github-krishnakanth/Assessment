package info.influx.Nearpod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.influx.Nearpod.model.campaign.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>, CampaignRepositoryEntityManaged {

}
