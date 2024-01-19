/**
 * 
 */
package rw.oredigital.com.v1.models.location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Aphrodice Rwagaju
 *
 */

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_location")
@Setter
public abstract class CalculationLocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * -- GETTER --
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	/**
	 * -- GETTER --
	 *
	 * @return the code
	 */
	private String code;
	/**
	 * -- GETTER --
	 *
	 * @return the name
	 */
	private String name;
	/**
	 * -- GETTER --
	 *
	 * @return the coordinate
	 */

	// TODO : lob bug to-solve
	@JsonIgnore
	@Lob
	private String coordinate;

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	boolean collectLocations(List<Location> locations, List<DataLocation> dataLocations, List<LocationLevel> skipLevels, List<DataLocationType> types) {
		boolean result = false;
		for (CalculationLocation child : getChildren(skipLevels)) {
			result = result | child.collectLocations(locations, dataLocations, skipLevels, types);
		}
		
		List<DataLocation> dataLocationsChildren = getDataLocations(skipLevels, types);
		if (!dataLocationsChildren.isEmpty()) {
			result = true;
			if (dataLocations != null) dataLocations.addAll(dataLocationsChildren);
		}
		
		if (result) {
			if (locations != null && !this.collectsData()) locations.add((Location) this);
		}
		return result;
	}
	
	/**
	 * Collects all data locations of the specified types that are children of this location. If types is
	 * {@code null}, returns all data locations that are children of this location.
	 *
	 * @return all data locations of the specified types.
	 */
	List<DataLocation> collectDataLocations(List<DataLocationType> types) {
		List<DataLocation> dataLocations = new ArrayList<DataLocation>();
		collectLocations(null, dataLocations, null, types);
		return dataLocations;
	}
	
	/**
	 * Returns all the data locations that are direct children of this location, and whose
	 * type is in the list specified by types. If this location is the parent of locations
	 * whose level is in the skipLevels list, it returns also the children's data locations.
	 *
	 * @param skipLevels if this location has children whose level is in this list, the data locations
	 * of those children are also returned. If skipLevels is {@code null}, skip levels are ignored.
	 * @param types returns only the data locations whose type is in this list, returns all data locations
	 * if types is {@code null}.
	 * @return the list of data locations.
	 */
	protected abstract List<DataLocation> getDataLocations(List<LocationLevel> skipLevels, List<DataLocationType> types);
	
	/**
	 * Returns all the location (data or not) that are direct children of this location. If this
	 * location is the parent of locations whose level is in the skipLevels list, it returns also
	 * the children's locations.
	 *
	 * @param skipLevels if this location has children whose level is in this list, the locations
	 * of those children are also returned. If skipLevels is {@code null}, skip levels are ignored.
	 * @return the list of locations
	 */
	protected abstract List<CalculationLocation> getChildren(List<LocationLevel> skipLevels);
	
	/**
	 * Navigates up through all the parents in the tree and returns the one with the specified level.
	 * If this location is of the specified level, it returns this location. If no parents have the
	 * specified level, it returns {@code null}.
	 *
	 * @param level the level of the parent we want to retrieve
	 * @return the parent with the specified level
	 */
	abstract Location getParentOfLevel(LocationLevel level);
	
	/**
	 * Returns true if this location collects data, false otherwise. If it returns
	 * true, then this object should be castable to DataLocation, otherwise
	 * it should cast to Location.
	 *
	 * @return true if this location collects data, false otherwise.
	 */
	abstract boolean collectsData();

}
