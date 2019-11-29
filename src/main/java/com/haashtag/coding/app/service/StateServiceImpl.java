/**
 * 
 */
package com.haashtag.coding.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.haashtag.coding.app.bean.StateBean;
import com.haashtag.coding.app.bean.StatusResponse;
import com.haashtag.coding.app.constants.Constants;
import com.haashtag.coding.app.model.District;
import com.haashtag.coding.app.model.State;
import com.haashtag.coding.app.repo.DistrictRepo;
import com.haashtag.coding.app.repo.StateRepo;

/**
 * @author Lakshmi Kiran
 *
 */
@Service("stateService")
public class StateServiceImpl implements StateService {

	Logger log = LoggerFactory.getLogger(StateServiceImpl.class);

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private DistrictRepo districtRepo;

	/**
	 * service to retrieve by state name
	 */
	@Override
	public StatusResponse getStates(String state) {

		if (log.isDebugEnabled()) {
			log.debug("StateService :: getStates :: Enter");
		}
		StatusResponse statusResponse;
		try {
			log.info("StateService :: getStates :: calling repository service");
			List<State> states = stateRepo.findStateByName(state);

			if (CollectionUtils.isEmpty(states)) {
				log.info("StateService :: getStates :: no states with name : " + state);

				StatusResponse response = new StatusResponse();

				response.setDescription("no states with name : " + state);
				response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
				response.setStatusCode(Constants.STATUS_CODE_FAILURE);

				return response;
			}

			List<StateBean> beans = new ArrayList<StateBean>();

			log.info("StateService :: getStates :: converting state to statebean");

			StatusResponse response = new StatusResponse();
			states.forEach(s -> {

				log.info("StateService :: getStates :: calling district repo service");
				List<District> districts = districtRepo.findDistrictByStateCode(s.getStateCode());

				if (CollectionUtils.isEmpty(districts)) {
					log.info("StateService :: getStates :: no districts for the state");

					response.setDescription("no states with name : " + state);
					response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					response.setStatusCode(Constants.STATUS_CODE_FAILURE);

					return;
				}

				log.info("StateService :: getStates :: updating district and states to state bean");
				districts.forEach(d -> {
					StateBean bean = new StateBean();

					bean.setDistrict(d.getDistrict());
					bean.setDistrictCode(d.getDistrictCode());
					bean.setState(s.getState());

					beans.add(bean);
				});
			});

			if (CollectionUtils.isEmpty(beans)) {
				return response;
			}

			statusResponse = new StatusResponse();

			statusResponse.setDescription("states retrieved successfully");
			statusResponse.setStatuMessage(Constants.STATUS_MESSAGE_SUCCESS);
			statusResponse.setStatusCode(Constants.STATUS_CODE_SUCCESS);
			log.info("StateService :: getStates :: states retrieved successfully");
			statusResponse.setStates(beans);

		} catch (Exception e) {
			statusResponse = new StatusResponse();

			statusResponse.setDescription("exception while retrieving states");
			statusResponse.setStatuMessage(Constants.STATUS_MESSAGE_EXCEPTION);
			statusResponse.setStatusCode(Constants.STATUS_CODE_EXCEPTION);
			log.error("StateService :: getStates :: exception while retrieving states", e);
			
			return statusResponse;
		}
		if (log.isDebugEnabled()) {
			log.debug("StateService :: getStates :: Exit");
		}
		return statusResponse;
	}

}
