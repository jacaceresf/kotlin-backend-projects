## MongoDB container 
```
docker run --name my_mongodb_cotainer -d -p 27017:27017 mongo:5.0.6
```
### Actions by parameters:
- `-name` we assign a custom name to the container, otherwise it will be a random name.
- `-d` runs the container in background, and it doesn't lock our terminal.
- `-p host_port:container_port` it simply binds the host port to the container port.
- In the end, we specify the image version that we would like to use.

## Reference:
- https://codersee.com/a-guide-to-ktor-with-mongodb/