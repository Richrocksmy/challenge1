### Using the service

Build the shadowJar:

``` 
./gradlew build
```

Build the docker container:

```
docker build -t service .
```

Start the service:

```
docker run -p 8080:8080 service
```
 