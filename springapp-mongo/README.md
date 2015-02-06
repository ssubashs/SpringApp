app-personal
============
Features : 

Base workspace for spring mvc & spring data rest api. 
H2 embedded database/ JPA configured for object modelling.
Mongo db :

$ "\bin\mongod.exe" --config "\Users\mongodb\mongod.cfg" --install
sc.exe create MongoDB binPath= "\mongodb-win32-x86_64-2008plus-2.6.5\bin\" --service --config=\"C:\Users\uswbdnu\Documents\mongodb\mongod.cfg\"" DisplayName= "MongoDB 2.6 Standard" start= "auto"
config folder has the folder structure needed for starting mongo service.
 
View -Thymeleaf/Freemarker/Jsp.
Rest enabled repositories. 

Demo details: 

junit testcase to load data to two collections - people/contact.

Spring hateos for mongo repositories. 
http://localhost:8080/springapp/app/

Halbrowser
http://localhost:8080/springapp-mongo/assets/pages/browser.html