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
 * @author Lakshmi Kiran
 *
 */
@Entity
@Table(name = "District")
public class District implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7385053795668350021L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "districtId")
	private Integer districtId;

	@Column(name = "district")
	private String district;

	@Column(name = "districtCode")
	private String districtCode;

	@Column(name = "stateCode")
	private Integer stateCode;

	@Column(name = "addedDate")
	private Date addedDate;

	/**
	 * @return the districtId
	 */
	public Integer getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
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
		return "District [districtId=" + districtId + ", district=" + district + ", districtCode=" + districtCode
				+ ", stateCode=" + stateCode + ", addedDate=" + addedDate + "]";
	}

}
