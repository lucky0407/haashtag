/**
 * 
 */
package com.haashtag.coding.app.bean;

import java.util.List;

/**
 * @author Lakshmi Kiran
 *
 */
public class StatusResponse {

	private int statusCode;
	
	private String statuMessage;
	
	private String description;
	
	private List<StateBean> states;
	
	private List<DistrictBean> districts;
	
	private TownBean towns;

	
	/**
	 * @return the states
	 */
	public List<StateBean> getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(List<StateBean> states) {
		this.states = states;
	}

	/**
	 * @return the districts
	 */
	public List<DistrictBean> getDistricts() {
		return districts;
	}

	/**
	 * @param districts the districts to set
	 */
	public void setDistricts(List<DistrictBean> districts) {
		this.districts = districts;
	}

	/**
	 * @return the towns
	 */
	public TownBean getTowns() {
		return towns;
	}

	/**
	 * @param towns the towns to set
	 */
	public void setTowns(TownBean towns) {
		this.towns = towns;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statuMessage
	 */
	public String getStatuMessage() {
		return statuMessage;
	}

	/**
	 * @param statuMessage the statuMessage to set
	 */
	public void setStatuMessage(String statuMessage) {
		this.statuMessage = statuMessage;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StatusResponse [statusCode=" + statusCode + ", statuMessage=" + statuMessage + ", description="
				+ description + ", states=" + states + ", districts=" + districts + ", towns=" + towns + "]";
	}
	
	
}
