/**
 * 
 */
package com.haashtag.coding.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.haashtag.coding.app.bean.StatusResponse;
import com.haashtag.coding.app.constants.Constants;
import com.haashtag.coding.app.model.District;
import com.haashtag.coding.app.model.State;
import com.haashtag.coding.app.model.Town;
import com.haashtag.coding.app.repo.DistrictRepo;
import com.haashtag.coding.app.repo.StateRepo;
import com.haashtag.coding.app.repo.TownRepo;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author Lakshmi Kiran
 *
 */
@Service("csvReaderService")
public class CsvReaderServiceImpl implements CsvReaderService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private TownRepo townRepo;

	@Autowired
	private DistrictRepo districtRepo;

	/**
	 * service to read csv file and update to database
	 * 
	 * @param reads csv file as multi part file
	 */
	@Override
	public StatusResponse readCSV(MultipartFile file) {

		if (log.isDebugEnabled()) {
			log.debug("CsvReaderServiceImpl :: readCSV :: Enter");
		}
		StatusResponse response;
		
		// working with try with resource
		try (Reader reader = Files.newBufferedReader(Paths.get(getFile(file).getAbsolutePath()));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) { // skipping the first line

			while (csvReader.readNext() != null) {
				// reading the csv file trough lines
				String[] next = csvReader.readNext();

				String town = next[1];
				String urbanStatus = next[2];
				String stateCode = next[3];
				String stateName = next[4];
				String districtCode = next[5];
				String district = next[6];

				log.info("CsvReaderServiceImpl :: readCSV :: checking state name and code are empty or not");
				if (!StringUtils.isEmpty(stateCode) && !StringUtils.isEmpty(stateName)) {
					response = new StatusResponse();
					response.setStatusCode(Constants.STATUS_CODE_FAILURE);
					response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					response.setDescription("state code or state cannot be empty");
					
					log.info("CsvReaderServiceImpl :: readCSV :: state code or state cannot be empty");

					return response;
				}
				saveState(stateName.trim(), Integer.valueOf(stateCode.trim()));

				log.info("CsvReaderServiceImpl :: readCSV :: checking district name and code are empty or not");
				if (!StringUtils.isEmpty(districtCode) && !StringUtils.isEmpty(district)) {
					response = new StatusResponse();
					response.setStatusCode(Constants.STATUS_CODE_FAILURE);
					response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					response.setDescription("district code or district cannot be empty");
					
					log.info("CsvReaderServiceImpl :: readCSV :: district code or district cannot be empty");

					return response;
				}
				saveDistrict(Integer.valueOf(districtCode.trim()), district.trim(), Integer.valueOf(stateCode));

				log.info("CsvReaderServiceImpl :: readCSV :: checking town name and urban status are empty or not");
				if (!StringUtils.isEmpty(town) && !StringUtils.isEmpty(urbanStatus)) {
					response = new StatusResponse();
					response.setStatusCode(Constants.STATUS_CODE_FAILURE);
					response.setStatuMessage(Constants.STATUS_MESSAGE_FAILURE);
					response.setDescription("urbanStatus or town cannot be empty");
					
					log.info("CsvReaderServiceImpl :: readCSV :: town name or urban status cannot be empty");
					
					if (log.isDebugEnabled()) {
						log.debug("CsvReaderServiceImpl :: readCSV :: Exit");
					}
					return response;
				}
				saveTown(urbanStatus.trim(), town.trim(), Integer.valueOf(stateCode.trim()),
						Integer.valueOf(districtCode.trim()));
			}
			
			response = new StatusResponse();
			response.setStatusCode(Constants.STATUS_CODE_SUCCESS);
			response.setStatuMessage(Constants.STATUS_MESSAGE_SUCCESS);
			response.setDescription("successfully read csv and date stored in db");
			
			log.info("CsvReaderServiceImpl :: readCSV :: successfully read csv and date stored in db");

			return response;
		} catch (Exception e) {
			log.error("exception while reading csv file",e);
			
			response = new StatusResponse();
			response.setStatusCode(Constants.STATUS_CODE_EXCEPTION);
			response.setStatuMessage(Constants.STATUS_MESSAGE_EXCEPTION);
			response.setDescription("exception while reading csv file");

			return response;
			
		}
	}

	/**
	 * service to convert multipart file to File
	 * 
	 * @param multipartFile
	 * 
	 * @return java.io.File'
	 * 
	 * @throws IOException
	 */
	private File getFile(MultipartFile multipartFile) throws IOException {

		File convFile = new File(multipartFile.getOriginalFilename());
		convFile.createNewFile();
		try (FileOutputStream fos = new FileOutputStream(convFile)) {
			fos.write(multipartFile.getBytes());
			return convFile;
		} catch (IOException e) {
			log.error("error while converting multipart file to file", e);
			return null;
		}
	}

	/**
	 * method to save state
	 * 
	 * @param stateName
	 * 
	 * @param stateCode
	 */
	private void saveState(String stateName, int stateCode) {
		log.info("CsvReaderServiceImpl :: saveState :: entering state dedails");
		State state = stateRepo.findStateByCode(Integer.valueOf(stateCode));
		if (null == state) {
			state = new State();
			state.setStateCode(Integer.valueOf(stateCode));
			state.setState(stateName);
			state.setAddedDate(new Date());
			log.info("CsvReaderServiceImpl :: saveState :: saving State in db");
			stateRepo.save(state);
			log.info("CsvReaderServiceImpl :: saveState :: state saved successfully");
		}
		log.info("CsvReaderServiceImpl :: saveState :: state already exists");
	}

	/**
	 * method to save district
	 * 
	 * @param districtCode
	 * 
	 * @param districtName
	 * 
	 * @param stateCode
	 */
	private void saveDistrict(int districtCode, String districtName, int stateCode) {
		log.info("CsvReaderServiceImpl :: saveDistrict :: entering district dedails");
		District district = districtRepo.findDistrictByCode(districtCode);
		if (null == district) {
			district = new District();
			district.setAddedDate(new Date());
			district.setDistrict(districtName);
			district.setDistrictCode(districtCode);
			district.setStateCode(stateCode);

			log.info("CsvReaderServiceImpl :: saveDistrict :: saving district dedails");
			districtRepo.save(district);
			log.info("CsvReaderServiceImpl :: saveState :: district saved successfully");
		}
		log.info("CsvReaderServiceImpl :: saveDistrict :: district already exists");
	}

	/**
	 * method to save town
	 * 
	 * @param urbanStatus
	 * 
	 * @param townName
	 * 
	 * @param stateCode
	 * 
	 * @param districtCode
	 */
	private void saveTown(String urbanStatus, String townName, int stateCode, int districtCode) {
		log.info("CsvReaderServiceImpl :: saveTown :: entering town dedails");
		Town town = townRepo.findTownByCodesandName(districtCode, stateCode, townName);
		if (null == town) {
			town = new Town();
			town.setAddedDate(new Date());
			town.setDistrictCode(districtCode);
			town.setStateCode(stateCode);
			town.setTown(townName);
			town.setUrbanStatus(urbanStatus);

			log.info("CsvReaderServiceImpl :: saveTown :: saving town dedails");
			townRepo.save(town);
			log.info("CsvReaderServiceImpl :: saveTown :: town saved successfully");
		}
		log.info("CsvReaderServiceImpl :: saveTown :: town already exists");
	}

}
