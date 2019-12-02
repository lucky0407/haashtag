/**
 * 
 */
package com.haashtag.coding.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haashtag.coding.app.model.District;

/**
 * @author Lakshmi Kiran
 *
 */
@Repository
public interface DistrictRepo extends JpaRepository<District, Integer>{

	@Query("select s from District s where s.districtCode =:districtCode and s.stateCode =:stateCode")
	public District findDistrictByCode(@Param("districtCode") String districtCode, @Param("stateCode") Integer stateCode);
	
	@Query("select s from District s where s.stateCode =:stateCode")
	public List<District> findDistrictByStateCode(@Param("stateCode") Integer stateCode);
	
	@Query("select s from District s where lower(s.district) like %:district%")
	public List<District> findDistrictsByName(@Param("district") String district);
}
