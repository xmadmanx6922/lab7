FROM java:latest  
COPY . /
WORKDIR /  
RUN javac ConnectMySQLDataBase.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.","ConnectMySQLDataBase"]
