package com.farmers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmers.model.TesignTrnTracker;
import com.farmers.model.TesignTrnTrackerPK;

public interface TesignTrnTrackerRepo extends JpaRepository<TesignTrnTracker, TesignTrnTrackerPK> {
	
public final static String findeSignTransbyPCN = "select esignTrans from TesignTrnTracker esignTrans where esignTrans.id.plcyContractNum = :policyNum";
	
	
	@Query(findeSignTransbyPCN)
	public List<TesignTrnTracker> findeSignTransbyPCN(@Param("policyNum")String policyNum);

}
