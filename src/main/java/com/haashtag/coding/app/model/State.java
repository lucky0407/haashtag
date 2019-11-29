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
@Table(name = "State")
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8202395379099031105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stateId")
	private Integer stateId;

	@Column(name = "state")
	private String state;

	@Column(name = "stateCode")
	private Integer stateCode;

	@Column(name = "addedDate")
	private Date addedDate;

	/**
	 * @return the stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", state=" + state + ", stateCode=" + stateCode + ", addedDate="
				+ addedDate + "]";
	}

}
