package com.farmers.batch.kyn;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportViewRepo extends JpaRepository<Reportview, ReportviewPK>  {
	
	public final static String findAgentreportquarter = "select reportview from Reportview reportview where reportview.id.agentnum = :aor and reportview.id.year = :year and reportview.id.quarter = :quarter";
	
	
	@Query(findAgentreportquarter)
	public List<Reportview> findAgentreportquarter(@Param("aor")String aor,@Param("year")Integer year,@Param("quarter") Integer quarter);

}
