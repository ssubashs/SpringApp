package com.app;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Repository;

/**
 * Factory to look up different strategies. Supports profile's based on the user's context.
 * @see CrytoStratergy
 */
@Repository
public class CryptoStratergyFactory {

    private static final Logger LOG = LoggerFactory.getLogger(CryptoStratergyFactory.class);
    
    @Autowired
    private ApplicationContext applicationContext;
    
    private Map<Class, CrytoStratergy> CrytoStratergyCache = new HashMap<Class, CrytoStratergy>();
    
    
    /**
     * Finds all beans annotated with CrytoStratergy. Does a quick sanity
     * check so only one CrytoStratergy exists for each profile.
     * @see CrytoStratergy
     */
    @PostConstruct
    public void init() {

        Map<String, Object> annotatedBeanClasses = applicationContext.getBeansWithAnnotation(CrytoStratergy.class);
        sanityCheck(annotatedBeanClasses.values());
        for (Object bean : annotatedBeanClasses.values()) {
        	CrytoStratergy CrytoStratergyAnnotation = CrytoStratergyCache.get(bean.getClass());
//            getBeansWithSameType(CrytoStratergyAnnotation).add(bean);
        }

    }

    
    
    private void sanityCheck(Collection<Object> annotatedBeanClasses) {
    	Set<String> usedStrategies = new HashSet<String>();

        for (Object bean : annotatedBeanClasses) {
            CrytoStratergy strategyAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), CrytoStratergy.class);
            CrytoStratergyCache.put(bean.getClass(), strategyAnnotation);
        }
		
	}


//    private List<Object> getBeansWithSameType(CrytoStratergy CrytoStratergyAnnotation) {
//        List<Object> beansWithSameType = annotatedTypes.get(CrytoStratergyAnnotation.type());
//        if (beansWithSameType != null) {
//            return beansWithSameType;
//        } else {
//            List<Object> newBeansList = new ArrayList<>();
//            annotatedTypes.put(CrytoStratergyAnnotation.type(), newBeansList);
//            return newBeansList;
//        }
//    }
//
//   
//    public <T> T getCrytoStratergy(Class<T> CrytoStratergyType) {
//
//        CrytoStratergy CrytoStratergyBeans = CrytoStratergyCache.get(CrytoStratergyType);
//        Assert.notEmpty(CrytoStratergyBeans, "No strategies found of type '"+ CrytoStratergyType.getName()+"', are the strategies marked with @CrytoStratergy?");
//
//        Object profileCrytoStratergy = findCrytoStratergyMatchingProfile(CrytoStratergyBeans, currentProfile);
//        if (profileCrytoStratergy == null) {
//            throw new RuntimeException("No CrytoStratergy found for type '"+ CrytoStratergyType +"'");
//        }
//        //noinspection unchecked
//        return (T)profileCrytoStratergy;
//    }

    
}