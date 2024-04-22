mvn clean install -DskipTests
docker build . -t olegvn88/books:0.0.1
docker push olegvn88/books:0.0.1
