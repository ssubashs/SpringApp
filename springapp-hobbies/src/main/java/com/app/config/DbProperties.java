package com.app.config;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbProperties extends Properties
{
	private final Logger logger = LoggerFactory.getLogger(DbProperties.class);
    private static final long serialVersionUID = 1L;
 
    public DbProperties(DataSource dataSource)
    {
        super();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
        List <Map<String,Object>> l = jdbcTemplate.queryForList("select prop_cd, prop_value from sys_props");
         
        for(Map<String,Object> m: l)
        {
            logger.debug("Loading from DB: [{}:{}]", m.get("prop_cd"), m.get("prop_value"));
            setProperty((m.get("prop_cd")).toString(), (m.get("prop_value")).toString());
        }
    }

}
