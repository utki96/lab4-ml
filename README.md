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
1. k : Indicates the number of points to use for knn.\
   Eg: `k=3`. Make sure not to add spaces in between
2. c : Indicates the Laplacian correction for Naive-Bayes.\
   Eg: `c=1`. Make sure not to add spaces in between
3. train : pass the training data file as this parameter.\
   Eg: `train=/home/utkarshtyg/Documents/knn/knn1.train.txt`. Make sure not to add spaces in between
4. test : pass the testing data file as this parameter.\
   Eg: `test=/home/utkarshtyg/Documents/knn/knn1.test.txt`. Make sure not to add spaces in between
5. d : Indicates the distance function **only for KMeans**: manh(Manhattan Distance)/e2(Euc squared).\
   Eg: `d=manh`. Make sure not to add spaces in between
6. centroid_value: pass the centroid values **only for KMeans**.\
   Eg: `200,200,200`. Make sure not to add spaces in between and to separate the dimensions with a ','.

### Examples to execute the code
1. KNN learner:\
Eg: ```java -jar target/learn-1.jar train=/home/utkarshtyg/Documents/knn/knn1.train.txt test=/home/utkarshtyg/Documents/knn/knn1.test.txt k=3```
2. Bayes learner:\
Eg: ```java -jar target/learn-1.jar train=/home/utkarshtyg/Documents/bayes/ex1_train.csv test=/home/utkarshtyg/Documents/bayes/ex1_test.csv c=1 v```
3. KMeans learner:\
Eg: ```java -jar target/learn-1.jar train=/home/utkarshtyg/Documents/kmeans/km1.txt 0,0 200,200 500,500```
