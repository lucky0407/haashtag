/**
 * 
 */
package com.haashtag.coding.app.bean;

/**
 * @author Lakshmi Kiran
 *
 */
public class DistrictBean {

	private String town;
	private String urbanStatus;
	private String districtCode;
	private String district;
	private Integer stateCode;
	private String state;

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "DistrictBean [town=" + town + ", urbanStatus=" + urbanStatus + ", districtCode=" + districtCode
				+ ", district=" + district + ", stateCode=" + stateCode + ", state=" + state + "]";
	}

}
