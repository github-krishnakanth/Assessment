package info.influx.Nearpod.model.campaign;

import java.time.LocalDateTime;

import javax.persistence.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import info.influx.Nearpod.common.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "campaign")
@Indexed
public class Campaign {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Fields({
            @Field,
            @Field(name = "name_sort", analyze = Analyze.NO, store = Store.YES, index = Index.YES)
    })
    @SortableField(forField = "name_sort")
    private String name;

    @Field(termVector = TermVector.YES)
    @Fields({
            @Field,
            @Field(name = "duration_sort", analyze = Analyze.NO, store = Store.YES, index = Index.YES)
    })
    @SortableField(forField = "duration_sort")
    private String duration;

    @Fields({
            @Field,
            @Field(name = "status_sort", analyze = Analyze.NO, store = Store.YES, index = Index.YES)
    })
    @SortableField(forField = "status_sort")
    private String status;

    @Field(analyze = Analyze.NO, store = Store.NO, index = Index.NO)
    private LocalDateTime startDate;

    @Field(analyze = Analyze.NO, store = Store.NO, index = Index.NO)
    private LocalDateTime endDate;

    @Field
    private String report;

    public Campaign() {
    }

    /*@PrePersist
    protected void () {
        duration = CampaignConverter.formatDatebyDateMonthYear.apply();
    }*/

    public Campaign(String name, String duration, String status, LocalDateTime startDate, LocalDateTime endDate, String report) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.report = report;
    }

    public static Builder<Campaign> builder() {
        return Builder.of(Campaign.class);
    }

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
