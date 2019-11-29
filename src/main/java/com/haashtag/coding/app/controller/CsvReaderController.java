/**
 * 
 */
package com.haashtag.coding.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haashtag.coding.app.bean.StatusResponse;
import com.haashtag.coding.app.constants.Constants;
import com.haashtag.coding.app.service.CsvReaderService;

/**
 * @author Lakshmi Kiran
 *
 */
@RestController
@RequestMapping("/csv")
public class CsvReaderController {

	Logger log = LoggerFactory.getLogger(CsvReaderController.class);

	@Autowired
	private CsvReaderService csvReaderService;

	/**
	 * controller service to read csv file
	 * 
	 * @param file
	 * 
	 * @return status of reader service
	 */
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseEntity<String> readCsv(@RequestParam("file") MultipartFile file) {

		if (log.isDebugEnabled()) {
			log.debug("CsvReaderController :: readCsv :: Enter");
		}

		log.info("CsvReaderController :: readCsv :: calling csvReaderService");
		StatusResponse response = csvReaderService.readCSV(file);

		if (response.getStatusCode() == Constants.STATUS_CODE_EXCEPTION) {
			log.info("CsvReaderController :: readCsv :: Exception");
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (response.getStatusCode() == Constants.STATUS_CODE_FAILURE) {
			log.info("CsvReaderController :: readCsv :: Failure");
			return new ResponseEntity<String>(response.getDescription(), HttpStatus.CONFLICT);
		}

		if (log.isDebugEnabled()) {
			log.debug("CsvReaderController :: readCsv :: Exit");
		}
		return new ResponseEntity<String>(response.getDescription(), HttpStatus.OK);

	}
}
