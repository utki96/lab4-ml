## System requirements to build and run the project:

1. Apache Maven - (3.6.0)
2. JDK - (1.8)
3. The code has been tested on `crackle1` server

## Steps:

1. Change the working directory to the root folder of this project.
2. Execute command: `mvn clean`
3. Execute command: `mvn package`
4. Now you have an executable jar in *"./target/learn-1.jar"*
5. Run this jar file by this command:\
   ```java -jar target/learn-1.jar [k=%d] [c=%d] [d=%s] [test=%s] [train=%s] [v] [centroid_values]```

## Arguments to the executable:
1. asd
2. asd
3. asd
4. asd
5. asd
6. asd

### Examples to execute the code
1. KNN learner:\
Eg: ```java -jar target/learn-1.jar df=1.0 min tol=0.01 iter=100 /home/utkarshtyg/Documents/restaurant.txt```
2. Bayes learner:\
Eg: ```java -jar target/learn-1.jar df=1.0 min tol=0.01 iter=100 /home/utkarshtyg/Documents/restaurant.txt```
3. KMeans learner:\
Eg: ```java -jar target/learn-1.jar df=1.0 min tol=0.01 iter=100 /home/utkarshtyg/Documents/restaurant.txt```
