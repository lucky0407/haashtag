/**
 * 
 */
package com.haashtag.coding.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haashtag.coding.app.model.Town;

/**
 * @author Lakshmi Kiran
 *
 */
@Repository
public interface TownRepo extends JpaRepository<Town, Integer> {

	@Query("select s from Town s where s.districtCode =:districtCode and s.stateCode =:stateCode and s.town =:town")
	public Town findTownByCodesandName(@Param("districtCode") String districtCode,
			@Param("stateCode") Integer stateCode, @Param("town") String town);

	@Query("select s from Town s where s.districtCode =:districtCode and s.stateCode =:stateCode")
	public List<Town> findTownByCodes(@Param("districtCode") String districtCode,
			@Param("stateCode") Integer stateCode);

	@Query("select s from Town s where lower(s.town) like %:town%")
	public Town findTownByName(@Param("town") String town);
}
