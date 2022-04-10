# Dockerize without maven plugin
```
Create a Dockerfile
Execute mvn clean package to generate jar file
Build docker image by executing 'docker build -f Dockerfile -t cenylcz/word-service:1.0.0 .'
Run docker by executing 'docker run -it -p 9000:8080 cenylcz/word-service:1.0.0'
```


#Dockerize with maven plugin
```
Add Maven plugin in pom.xml
Create a DockerFile (Pay attention to args)
Execute 'mvn clean package dockerfile:build'
Run docker by executing 'docker run -it -p 9000:8080 cenylcz/word-service:1.0.0'