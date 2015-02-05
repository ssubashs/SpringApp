Springapp-Batch
============
Features : 

Base workspace for spring mvc & spring data rest api. 
JPA configured for object modelling. 
mysql setup file with data - batch_smartoffice\src\main\resources\META-INF\dbsetup.sql

1) batch_smartoffice 
	- java project with spring batch examples
	- sample dozer configuration/mapping
	- unit test case to run each batch. 
	- drools - fire rules from a rule configured in xml/db on java collections. 
	
2) batchadmin
	- spring batch admin console configured to pick jobs from batch_smartoffice.
	- http://localhost:8080/batchadmin/
	- webapp/styles has the styles to overwrite the default app setting. 
	- webapp\WEB-INF\web\layouts\html has the ftls to overwrite the admin UI.
	- dependent spring admin source projects  spring-batch-admin-* and spring-batch-* should be build prior to this project build.
	
3) batchapp
	- jasper for downloading reports in pdf and excel
	- thymeleaf templates for html report.
	- jasper subreports



