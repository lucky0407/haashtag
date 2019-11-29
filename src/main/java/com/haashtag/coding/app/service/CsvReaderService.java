/**
 * 
 */
package com.haashtag.coding.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.haashtag.coding.app.bean.StatusResponse;

/**
 * @author Lakshmi Kiran
 *
 */
public interface CsvReaderService {

	public StatusResponse readCSV(MultipartFile file);
}
