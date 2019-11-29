/**
 * 
 */
package com.haashtag.coding.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haashtag.coding.app.bean.StatusResponse;
import com.haashtag.coding.app.bean.TownBean;
import com.haashtag.coding.app.constants.Constants;
import com.haashtag.coding.app.model.District;
import com.haashtag.coding.app.model.State;
import com.haashtag.coding.app.model.Town;
import com.haashtag.coding.app.repo.DistrictRepo;
import com.haashtag.coding.app.repo.StateRepo;
import com.haashtag.coding.app.repo.TownRepo;

/**
 * @author Lakshmi Kiran
 *
 */
@Service("townService")
public class TownServiceImpl implements TownService {

	Logger log = LoggerFactory.getLogger(TownServiceImpl.class);

	@Autowired
	private DistrictRepo districtRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private TownRepo townRepo;
	
	
	/**
	 * service to retrieve town details by town name
	 */
	@Override
	public StatusResponse getTowns(String townname) {

		if (log.isDebugEnabled()) {
			log.debug("TownService :: getTowns :: Enter");
		}

		StatusResponse response;
		
		try {
			log.info("TownService :: getTowns :: calling town repo service");
			Town town = townRepo.findTownByName(townname);
			
			if(null == town) {
				log.info("TownService :: getTowns :: no town with town name : "+townname);
				response = new StatusResponse();
				response.setDescription("no town with town name : " + townname);
				response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
				response.setStatusCode(Constants.STATUS_CODE_FAILURE);

				return response;
			}
			
			log.info("TownService :: getTowns :: calling state repo service");
			State state = stateRepo.findStateByCode(town.getStateCode());
			if(null == state) {
				log.info("TownService :: getTowns :: no state added for town with town name : "+townname);
				response = new StatusResponse();
				response.setDescription("no state added for town with town name : " + townname);
				response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
				response.setStatusCode(Constants.STATUS_CODE_FAILURE);

				return response;
			}
			
			log.info("TownService :: getTowns :: calling district repo service");
			District district = districtRepo.findDistrictByCode(town.getDistrictCode());
			if(null == district) {
				log.info("TownService :: getTowns :: no district added for town with town name : "+townname);
				response = new StatusResponse();
				response.setDescription("no district added for town with town name : " + townname);
				response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
				response.setStatusCode(Constants.STATUS_CODE_FAILURE);

				return response;
			}
			
			log.info("TownService :: getTowns :: updating town details to town bean");
			TownBean bean = new TownBean();
			
			bean.setDistrict(district.getDistrict());
			bean.setState(state.getState());
			bean.setTown(town.getTown());
			
			response = new StatusResponse();

			response.setDescription("town details retrieved successfully");
			response.setStatuMessage(Constants.STATUS_MESSAGE_SUCCESS);
			response.setStatusCode(Constants.STATUS_CODE_SUCCESS);
			
			response.setTowns(bean);
			
		} catch (Exception e) {
			response = new StatusResponse();

			response.setDescription("exception while retrieving towns");
			response.setStatuMessage(Constants.STATUS_MESSAGE_EXCEPTION);
			response.setStatusCode(Constants.STATUS_CODE_EXCEPTION);
			log.error("StateService :: getStates :: exception while retrieving towns", e);
			
			return response;
		}
		if (log.isDebugEnabled()) {
			log.debug("TownService :: getTowns :: Exit");
		}
		return response;
	}

}
