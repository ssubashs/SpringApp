package com.app.config;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class DbPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer
{
	 @Override
	    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
	    {
	        DataSource dataSource = (DataSource) beanFactory.getBean("propsDS");
	        DbProperties dbProps = new DbProperties(dataSource);
	         
	        setProperties(dbProps);
	        super.postProcessBeanFactory(beanFactory);
	    }

}
