/**
 * 
 */
package com.haashtag.coding.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lakshmikirant
 *
 */
@Entity
@Table(name = "Town")
public class Town implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4772960601831308231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "townId")
	private Integer townId;

	@Column(name = "town")
	private String town;

	@Column(name = "urbanStatus")
	private String urbanStatus;

	@Column(name = "stateCode")
	private Integer stateCode;

	@Column(name = "districtCode")
	private String districtCode;

	@Column(name = "addedDate")
	private Date addedDate;

	/**
	 * @return the cityId
	 */
	public Integer getTownId() {
		return townId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setTownId(Integer cityId) {
		this.townId = cityId;
	}

	/**
	 * @return the city
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param city the city to set
	 */
	public void setTown(String city) {
		this.town = city;
	}

	/**
	 * @return the urbanStatus
	 */
	public String getUrbanStatus() {
		return urbanStatus;
	}

	/**
	 * @param urbanStatus the urbanStatus to set
	 */
	public void setUrbanStatus(String urbanStatus) {
		this.urbanStatus = urbanStatus;
	}

	/**
	 * @return the stateCode
	 */
	public Integer getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "City [cityId=" + townId + ", city=" + town + ", urbanStatus=" + urbanStatus + ", stateCode=" + stateCode
				+ ", districtCode=" + districtCode + ", addedDate=" + addedDate + "]";
	}

}
