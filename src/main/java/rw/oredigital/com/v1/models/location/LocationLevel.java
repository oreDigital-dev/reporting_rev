/**
 * 
 */
package rw.oredigital.com.v1.models.location;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "location_level")
@NoArgsConstructor
@AllArgsConstructor
public class LocationLevel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;
	private String name;

	@Column(name="order_number")
	private int orderName;

	@OneToMany(cascade= CascadeType.ALL, mappedBy = "level",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Location> locations;
	
//	@OneToMany(cascade= CascadeType.ALL, mappedBy = "level")
//	private List<HealthFacilityPackage> healthFacilityPackages;
	
	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}
	/**
	 * @param locations the locations to set
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the orderName
	 */
	public int getOrderName() {
		return orderName;
	}
	/**
	 * @param orderName the order to set
	 */
	public void setOrderName(int orderName) {
		this.orderName = orderName;
	}
	/**
	 * @param code
	 * @param name
	 * @param orderName
	 */
	public LocationLevel(String code, String name, int orderName) {
		this.code = code;
		this.name = name;
		this.orderName = orderName;
	}
}