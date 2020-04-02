package info.influx.Nearpod.service.impl.campaign;

import java.util.List;

import info.influx.Nearpod.common.FunctionUtils;
import info.influx.Nearpod.common.Promise;
import info.influx.Nearpod.common.React;
import info.influx.Nearpod.common.Reader;
import info.influx.Nearpod.model.campaign.CampaignFilter;
import info.influx.Nearpod.model.campaign.CampaignInfo;
import info.influx.Nearpod.repository.CampaignRepositoryEntityManagedImpl;

public class CampaignFacade {
	
	public static Reader<CampaignRepositoryEntityManagedImpl, Promise<List<CampaignInfo>>> searchByCampaignData(final CampaignFilter campaignFilter) {
        return Reader.of(managedImpl -> React.of(() -> campaignFilter)
                .then(impl -> managedImpl.searchByCampaignData(campaignFilter))
                .then(FunctionUtils.asList(CampaignConverter::convertToInfo))
                .getPromise());
    }
}
