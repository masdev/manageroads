# manageroads
Simple  Spring Boot and Spring REST project template



How to run docker image

Build maven script: maven clean install
Run in console the next command (Docker service must be up): docker build -t manageroads:latest .
Check if docker image was created: docker images
Run image: docker run -p 8081:8080 manageroads