/**
 * 
 */
package rw.oredigital.com.v1.models.location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rw.oredigital.com.v1.enums.EVisibility;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
@Table(name = "data_location_types")
@NoArgsConstructor
@AllArgsConstructor
public class DataLocationType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	// Order is a keyword and cannot be a field
	@Column(name = "order_number")
	private String orderNumber;
	private String name;


	private EVisibility visibility = EVisibility.VISIBLE;
	@Column(name = "default_selected")
	private boolean defaultSelected;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch = FetchType.LAZY)
	@JsonIgnore
	List<DataLocation> dataLocations;

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
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the defaultSelected
	 */
	public boolean isDefaultSelected() {
		return defaultSelected;
	}

	/**
	 * @param defaultSelected the defaultSelected to set
	 */
	public void setDefaultSelected(boolean defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	/**
	 * @return the dataLocations
	 */
	public List<DataLocation> getDataLocations() {
		return dataLocations;
	}

	/**
	 * @param dataLocations the dataLocations to set
	 */
	public void setDataLocations(List<DataLocation> dataLocations) {
		this.dataLocations = dataLocations;
	}

	/**
	 * @param code
	 * @param order
	 * @param name
	 * @param defaultSelected
	 */
	public DataLocationType(String code, String orderNumber, String name, boolean defaultSelected) {
		this.code = code;
		this.orderNumber = orderNumber;
		this.name = name;
		this.defaultSelected = defaultSelected;
	}

	public String toString() {
		return "DataLocationType[Code=" + code + "]";
	}

}
