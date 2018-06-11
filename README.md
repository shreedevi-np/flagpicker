# Flagpicker Project task
How to setup?

1. Clone the project.
2. Run ```mvn clean install``` to install all dependencies.
2. Run ```mvn package```, test and build.
3. Apply the ```schema.sql``` to MySQL (create the schema) and also execute the ```data.sql``` to load data.
** All API's are protected by ```auth-token```, which must be added to header. The token can be configurable, see ```fp.security.token``` in ```application.properties``` file.
4. End points:
   prefix ```/api/v1``` for all search API's.
      - Get countries of the continent ```/continents/{continentName}```
      - Get flag for countries ```/flags/{countriesName}```, countriesName must be comma separated list
      
   prefix ```metrics``` for all metrics.
      - Get full metrics ```/```
      - Get status metrics ```/status```
      - Get metrics in data series to plot in the graph ```/graph```
      
   Audit, use actuator enpoints.
