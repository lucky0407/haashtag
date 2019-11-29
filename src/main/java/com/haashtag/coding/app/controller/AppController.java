/**
 * 
 */
package com.haashtag.coding.app.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haashtag.coding.app.bean.DistrictBean;
import com.haashtag.coding.app.bean.StateBean;
import com.haashtag.coding.app.bean.StatusResponse;
import com.haashtag.coding.app.bean.TownBean;
import com.haashtag.coding.app.constants.Constants;
import com.haashtag.coding.app.service.DistrictService;
import com.haashtag.coding.app.service.StateService;
import com.haashtag.coding.app.service.TownService;

/**
 * @author Lakshmi Kiran
 *
 */
@RestController
public class AppController {

	Logger log = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private TownService townService;
	
	/**
	 * service to retrieve state details by name
	 * 
	 * @param name
	 * 
	 * @return ResponseEntity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public ResponseEntity getStateDetails(@PathParam("name") String name) {
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getStateDetails :: Enter");
		}
		
		if(isValidString(name))
			return new ResponseEntity<String>("Invalid state name", HttpStatus.BAD_REQUEST);
		
		log.info("AppController :: getStateDetails :: calling state service");
		StatusResponse response = stateService.getStates(name);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_FAILURE)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.CONFLICT);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_EXCEPTION)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getStateDetails :: Exit");
		}
		return new ResponseEntity<List<StateBean>>(response.getStates(), HttpStatus.OK);
	}
	
	/**
	 * service to retrieve district details by name
	 * 
	 * @param name
	 * 
	 * @return ResponseEntity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/district", method = RequestMethod.GET)
	public ResponseEntity getDistrictDetails(@PathParam("name") String name) {
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getDistrictDetails :: Enter");
		}
		
		if(isValidString(name))
			return new ResponseEntity<String>("Invalid district name", HttpStatus.BAD_REQUEST);
		
		log.info("AppController :: getDistrictDetails :: calling state service");
		StatusResponse response = districtService.getDistricts(name);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_FAILURE)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.CONFLICT);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_EXCEPTION)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getDistrictDetails :: Exit");
		}
		return new ResponseEntity<List<DistrictBean>>(response.getDistricts(), HttpStatus.OK);
	}
	
	/**
	 * service to retrieve town details by name
	 * 
	 * @param name
	 * 
	 * @return ResponseEntity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/town", method = RequestMethod.GET)
	public ResponseEntity getTownDetails(@PathParam("name") String name) {
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getTownDetails :: Enter");
		}
		
		if(isValidString(name))
			return new ResponseEntity<String>("Invalid town name", HttpStatus.BAD_REQUEST);
		
		log.info("AppController :: getTownDetails :: calling state service");
		StatusResponse response = townService.getTowns(name);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_FAILURE)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.CONFLICT);
		
		if(response.getStatusCode() == Constants.STATUS_CODE_EXCEPTION)
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(log.isDebugEnabled()) {
			log.debug("AppController :: getTownDetails :: Exit");
		}
		return new ResponseEntity<TownBean>(response.getTowns(), HttpStatus.OK);
	}
	
	/**
	 * service to validate whether string contains only characters or not
	 * 
	 * @param str
	 * 
	 * @return
	 */
	private boolean isValidString(String str) {
		
		return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
	}
}
