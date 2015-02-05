package com.farmers.batch.kyn;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgentDetailsRepo extends JpaRepository<Agentdetail, String>  {
	
public final static String findAgentbyAor = "select agent from Agentdetail agent where agent.agentNum = :aor";
	
	
	@Query(findAgentbyAor)
	public List<Agentdetail> findAgentbyAor(@Param("aor")String aor);


}
