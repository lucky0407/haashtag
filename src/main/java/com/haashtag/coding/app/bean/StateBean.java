/**
 * 
 */
package com.haashtag.coding.app.bean;

/**
 * @author Lakshmi Kiran
 *
 */
public class StateBean {
	
	private String state;
	private Integer districtCode;
	private String district;
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
	/**
	 * @return the districtCode
	 */
	public Integer getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(Integer districtCode) {
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
	@Override
	public String toString() {
		return "StateBean [state=" + state + ", districtCode=" + districtCode + ", district=" + district + "]";
	}
	
	
}
