package com.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class ModularPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

	 Map<String, List<String>> puiClasses = new HashMap<String, List<String>>();

	 public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui)
	 {
	  List<String> classes = puiClasses.get(pui.getPersistenceUnitName());
	  if (classes == null)
	  {
	   classes = new ArrayList<String>();
	   puiClasses.put(pui.getPersistenceUnitName(), classes);
	  }
	  pui.getManagedClassNames().addAll(classes);

	  final List<String> names = pui.getManagedClassNames();
	  classes.addAll(pui.getManagedClassNames());
	 }
}
