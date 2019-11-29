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

import com.haashtag.coding.app.bean.DistrictBean;
import com.haashtag.coding.app.bean.StatusResponse;
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
@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

	Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);

	@Autowired
	private DistrictRepo districtRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private TownRepo townRepo;

	/**
	 * service to retrieve district details by district name
	 */
	@Override
	public StatusResponse getDistricts(String district) {

		if (log.isDebugEnabled()) {
			log.debug("DistrictService :: getDistricts :: Enter");
		}

		StatusResponse response;
		try {
			log.info("DistrictService :: getDistricts :: calling district repo service");
			List<District> districts = districtRepo.findDistrictsByName(district);

			if (CollectionUtils.isEmpty(districts)) {
				log.info("DistrictService :: getDistricts :: no districts with district name : " + district);

				response = new StatusResponse();
				response.setDescription("no districts with district name : " + district);
				response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
				response.setStatusCode(Constants.STATUS_CODE_FAILURE);

				return response;
			}

			StatusResponse sr = new StatusResponse();
			List<DistrictBean> beans = new ArrayList<DistrictBean>();
			districts.forEach(d -> {

				log.info("DistrictService :: getDistricts ::calling state repo service");

				State s = stateRepo.findStateByCode(d.getStateCode());
				if (null == s) {
					sr.setDescription("no state with state name");
					sr.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					sr.setStatusCode(Constants.STATUS_CODE_FAILURE);

					log.info("DistrictService :: getDistricts :: no state added for district : " + district);
					return;
				}

				log.info("DistrictService :: getDistricts ::calling town repo service");

				List<Town> towns = townRepo.findTownByCodes(d.getDistrictCode(), s.getStateCode());
				if (CollectionUtils.isEmpty(towns)) {
					sr.setDescription("no towns added in district : " + district);
					sr.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					sr.setStatusCode(Constants.STATUS_CODE_FAILURE);

					log.info("DistrictService :: getDistricts :: no towns added in district : " + district);
					return;
				}

				log.info("DistrictService :: getDistricts :: updating district details to district bean");
				towns.forEach(t -> {
					DistrictBean bean = new DistrictBean();
					bean.setDistrict(d.getDistrict());
					bean.setDistrictCode(d.getDistrictCode());
					bean.setState(s.getState());
					bean.setStateCode(s.getStateCode());
					bean.setTown(t.getTown());
					bean.setUrbanStatus(t.getUrbanStatus());

					beans.add(bean);
				});
			});

			if (CollectionUtils.isEmpty(beans)) {
				log.info("DistrictService :: getDistricts :: " + sr.getDescription());
				return sr;
			}

			response = new StatusResponse();

			response.setDescription("district details retrieved successfully");
			response.setStatuMessage(Constants.STATUS_MESSAGE_SUCCESS);
			response.setStatusCode(Constants.STATUS_CODE_SUCCESS);
			
			response.setDistricts(beans);
		} catch (Exception e) {
			response = new StatusResponse();

			response.setDescription("exception while retrieving districts");
			response.setStatuMessage(Constants.STATUS_MESSAGE_EXCEPTION);
			response.setStatusCode(Constants.STATUS_CODE_EXCEPTION);
			log.error("StateService :: getStates :: exception while retrieving districts", e);
			
			return response;
		}
		if (log.isDebugEnabled()) {
			log.debug("DistrictService :: getDistricts :: Exit");
		}
		return response;
	}

}
