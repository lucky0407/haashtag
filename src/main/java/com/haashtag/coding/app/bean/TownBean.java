/**
 * 
 */
package com.haashtag.coding.app.bean;

/**
 * @author Lakshmi Kiran
 *
 */
public class TownBean {

	private String town;
	private String district;
	private String state;

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
		return "CityBean [city=" + town + ", district=" + district + ", state=" + state + "]";
	}

}
