How to run docker image

1) Build maven script: maven clean install

2) Run in console the next command (Docker service must be up): docker build -t manageroads:latest .

3) Check if docker image was created: docker images

4) Run image: docker run -p 8081:8080 manageroads