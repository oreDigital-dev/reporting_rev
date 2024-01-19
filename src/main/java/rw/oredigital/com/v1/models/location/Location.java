/**
 * 
 */
package rw.oredigital.com.v1.models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
public class Location extends CalculationLocation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "parent_location_id", nullable = true)
	private Location parent;
	@ManyToOne
	@JoinColumn(name = "location_level_id")
	private LocationLevel level;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "managedBy", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DataLocation> dataLocations;
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Location> children;

//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "location")
//	private List<Patient> patients;
	

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public LocationLevel getLevel() {
		return level;
	}

	public void setLevel(LocationLevel level) {
		this.level = level;
	}

	public List<DataLocation> getDataLocations() {
		return dataLocations;
	}

	public void setDataLocations(List<DataLocation> dataLocations) {
		this.dataLocations = dataLocations;
	}

	public List<Location> getChildren() {
		return children;
	}

	public void setChildren(List<Location> children) {
		this.children = children;
	}

	@Override
	public List<CalculationLocation> getChildren(List<LocationLevel> skipLevels) {
		List<CalculationLocation> result = new ArrayList<CalculationLocation>();
		for (Location child : children) {
			if (skipLevels != null && skipLevels.contains(child.level)) {
				result.addAll(child.getChildren(skipLevels));
			}
			else result.add(child);
		}
		return result;
	}
	
	@Override
	public List<DataLocation> getDataLocations(List<LocationLevel> skipLevels, List<DataLocationType> types) {
		
		List<DataLocation> result = new ArrayList<DataLocation>();
//		for (DataLocation dataLocation : dataLocations) {
//			if (types == null || types.contains(dataLocation.getType())) 
//				result.add(dataLocation);
//		}
		
		for (Location child : children) {
			if (skipLevels != null && skipLevels.contains(child.level)) {
				result.addAll(child.getDataLocations(skipLevels, types));
			}
		}
		
		return result;				
	}
	
	@Override
	public Location  getParentOfLevel(LocationLevel level) {
		if (this.level.equals(level)) return this;
		else return parent.getParentOfLevel(level);
	}
	
	@Override
	boolean collectsData() {
		return false;
	}
	
	/**
	 * Returns all the direct children whose tree contain at least one data location. If this
	 * location is the parent of locations whose level is in the skipLevels list, it returns also the
	 * children's direct children who meets the same criteria.
	 *
	 * @param skipLevels if this location has children whose level is in this list, the children
	 * of those children are also returned. If skipLevels is {@code null}, skip levels are ignored.
	 * @param types returns only the data locations whose type is in this list, returns all data locations
	 * if types is {@code null}.
	 * @return all direct children whose tree contain at least one data location, taking into account the skip levels 
	 * and whose type is in the specified list
	 */
	List<CalculationLocation> getChildrenEntitiesWithDataLocations(List<LocationLevel> skipLevels, List<DataLocationType> types) {
		//if (log.debugEnabled) log.debug("getChildrenEntitiesWithDataLocations(${skipLevels}, ${types})")
		List<CalculationLocation> result = new ArrayList<CalculationLocation>();
		
		List<CalculationLocation> locationChildren = getChildren(skipLevels);
		List<Location> locationTree = collectTreeWithDataLocations(skipLevels, types);
		for(CalculationLocation locationChild : locationChildren){
			if(locationTree.contains(locationChild))
				result.add(locationChild);	
		}
		
		//if (log.debugEnabled) log.debug("getChildrenEntitiesWithDataLocations(...)=${result}")
		return result;
	}
	
	/**
	 * Collects all locations of the specified types that are children of this location and
	 * whose tree contains at least one data location whose type is in types. If types is
	 * {@code null}, returns all locations that are children of this location and whose tree contains
	 * at least one data location. Does not include the locations that are in the skipLevels list.
	 * 
	 * @param skipLevels ignores location whose level is in this list
	 * @param types only considers data location types that are in this list
	 * @return all locations of the specified types that are children of this location and whose tree contains at least
	 * one data location whose type is in types
	 */
	List<Location> collectTreeWithDataLocations(List<LocationLevel> skipLevels, List<DataLocationType> types) {
		List<Location> locations = new ArrayList<Location>();
		collectLocations(locations, null, skipLevels, types);
		return locations;
	}
	
	
	
	/**
	 * @param parent
	 * @param level
	 */
	public Location(Location parent, LocationLevel level) {
		this.parent = parent;
		this.level = level;
	}

	public String toString() {
		return "Location[Code=" + getCode() + "]";
	}
	
}
