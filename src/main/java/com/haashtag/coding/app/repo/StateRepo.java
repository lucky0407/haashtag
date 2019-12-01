/**
 * 
 */
package com.haashtag.coding.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haashtag.coding.app.model.State;

/**
 * @author Lakshmi Kiran
 *
 */
@Repository
public interface StateRepo extends JpaRepository<State, Integer>{

	@Query("select s from State s where s.stateCode =:stateCode")
	public State findStateByCode(@Param("stateCode") Integer stateCode);
	
	@Query("select s from State s where s.state like %:state%")
	public List<State> findStateByName(@Param("state") String state);
}
