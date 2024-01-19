/**
 *
 */
package rw.oredigital.com.v1.models.location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import rw.oredigital.com.v1.enums.EDataLocationType;
import rw.oredigital.com.v1.enums.EVisibility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
@Table(name = "data_location")
@Setter
@Getter
@AllArgsConstructor
public class DataLocation extends CalculationLocation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private EVisibility visibility = EVisibility.VISIBLE;

	private EDataLocationType dataLocationType;

	@Column(name="needs_review")
	private boolean needsReview;

	@Column(name="date_created")
	private Date dateCreated;


	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private DataLocationType type;

	@Column(name = "insurance_institution_id", nullable = true, unique = true)
	private String insuranceInstitutionCode;

	@ManyToOne
	@JoinColumn(name = "managed_by_id")
	private DataLocation managedBy;

	@OneToMany(cascade= CascadeType.ALL, mappedBy = "managedBy")
	@JsonIgnore
	private List<DataLocation> children;

	/**
	 * @return the needsReview
	 */
	public boolean isNeedsReview() {
		return needsReview;
	}

	/**
	 * @param needsReview the needsReview to set
	 */
	public void setNeedsReview(boolean needsReview) {
		this.needsReview = needsReview;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the type
	 */
	public DataLocationType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DataLocationType type) {
		this.type = type;
	}

	/**
	 * @return the managedBy
	 */
	public DataLocation getManagedBy() {
		return managedBy;
	}

	/**
	 * @param managedBy the managedBy to set
	 */
	public void setManagedBy(DataLocation managedBy) {
		this.managedBy = managedBy;
	}

	/**
	 * @return the children
	 */
	public List<DataLocation> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<DataLocation> children) {
		this.children = children;
	}

	@Override
	public  List<CalculationLocation> getChildren(List<LocationLevel> skipLevels) {
		List<CalculationLocation> result = new ArrayList<CalculationLocation>();
		for (DataLocation child : children) {
			if (skipLevels != null && skipLevels.contains(child.getLocation().getLevel())) {
				result.addAll(child.getChildren(skipLevels));
			}
			else result.add(child);
		}
		return result;
	}

	@Override
	public List<DataLocation> getDataLocations(List<LocationLevel> skipLevels, List<DataLocationType> types) {
		ArrayList<DataLocation> result = new ArrayList<DataLocation>();
		if (types == null || types.contains(type)) result.add(this);
		return result;
	}

	@Override
	Location getParentOfLevel(LocationLevel level) {
		return this.location.getParentOfLevel(level);
	}

	/**
	 * honore
	 */
	public DataLocation() {
	}

	/**
	 * @param needsReview
	 * @param dateCreated
	 * @param location
	 * @param type
	 * @param managedBy
	 */
	public DataLocation(boolean needsReview, Date dateCreated, Location location, DataLocationType type,
						DataLocation managedBy) {
		this.needsReview = needsReview;
		this.dateCreated = dateCreated;
		this.location = location;
		this.type = type;
		this.managedBy = managedBy;
	}

	@Override
	boolean collectsData() {
		return true;
	}

}