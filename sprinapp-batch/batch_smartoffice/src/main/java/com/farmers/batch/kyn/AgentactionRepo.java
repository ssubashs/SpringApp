package com.farmers.batch.kyn;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgentactionRepo extends JpaRepository<Agentaction, AgentactionPK>  {
	
public final static String findAgentactionquarter = "select action from Agentaction action where action.id.agentNum = :aor and action.id.year = :year and action.id.month <= :quarter*3 and action.id.month > (:quarter -1)*3 order by action.id.month desc";
	
	
	@Query(findAgentactionquarter)
	public List<Agentaction> findAgentreportquarter(@Param("aor")String aor,@Param("year")Integer year,@Param("quarter") Integer quarter);

}
