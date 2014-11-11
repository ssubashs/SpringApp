/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;


public class JtaPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(JtaPersistenceUnitPostProcessor.class);

    private DataSource dataSource;
    private boolean jtaEnabled = false;

    /**
     * Enriches the PersistenceUnitInfo read from the <tt>persistence.xml</tt>
     * configuration file according to the <tt>jtaEnabled</tt> flag. Registers
     * the <tt>dataSource</tt> as a jta data source if it is <tt>true</tt> or
     * as a regular, non-jta data source otherwise.
     *
     * @see PersistenceUnitPostProcessor#postProcessPersistenceUnitInfo(org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo)
     */
    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo mutablePersistenceUnitInfo) {
        if (jtaEnabled) {
            if (logger.isDebugEnabled()) {
                logger.debug("Enriching the persistence unit info with the jta aware data source.");
            }
            mutablePersistenceUnitInfo.setJtaDataSource(dataSource);
            mutablePersistenceUnitInfo.setTransactionType(PersistenceUnitTransactionType.JTA);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Enriching the persistence unit info with the non-jta aware data source.");

            }
            mutablePersistenceUnitInfo.setNonJtaDataSource(dataSource);
            mutablePersistenceUnitInfo.setTransactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);
        }
    }

    /**
     * Sets the {@link DataSource} to be used by the entity manager factory
     *
     * @param dataSource the data source to use
     */
    @Required
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Specifies if the data source should be injected as a jta-aware dataSource
     * in the entity manager.
     *
     * @param jtaEnabled <tt>true</tt> to enable jta support
     */
    public void setJtaEnabled(boolean jtaEnabled) {
        this.jtaEnabled = jtaEnabled;
    }

}
