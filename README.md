# url-shortie 📏

This little [Spring Boot](https://spring.io/projects/spring-boot) service will return a shortened URL when given a URL. It can also return the original URL when given the shortened URL. 

It uses [Redis](https://redis.io/) as its data store, which is spun up in a [Docker](https://www.docker.com/) container for convenience. 

## Getting Started 🏁

Git clone this repo onto your machine.

Git clone using SSH:
```shell script
git clone git@github.com:emily599/url-shortie.git
```

Git clone using HTTPS:
```shell script
git clone https://github.com/emily599/url-shortie.git
```

### Prerequisites ✅
You will need the following locally installed! 

- [Docker](https://www.docker.com/) - to run containers
- [Java](https://www.java.com/en/) - programming language to write with
- [Maven](https://maven.apache.org/) - to manage your Java project
- [Postman](https://www.postman.com/) - to send API requests with 

## How to use url-shortie service ⚙️

1. Spin up a local Redis instance using Docker. Make sure you are at the root of the repository!
```shell script
make redis-up
```
2. Start the Spring Boot application with the following command:
```
mvn spring-boot:run
```
### Using command line 
1. To shorten a URL, send a curl command as shown below:
```
curl http://localhost:8089/url -H "Content-Type: text/plain" -d "{URL}"
```
Here is an example with `https://dolly.com/about/`:

![curlGetId](images/curlGetId.png?raw=true)

2. To retrieve the original URL, send a curl command as shown below:
```
curl -X GET http://localhost:8089/url/{id} -H "Content-Type: text/plain"
```
Here is an example with ID `d3cccd90`:

![curlGetId](images/curlGetUrl.png?raw=true)

### Using Postman

3. To shorten a URL, open up Postman and send a POST request to `http://localhost:8089/url`. The body of the request should just be the URL, and make sure the body type is `raw` and set to `Text`.

![getIdFromUrl](images/getIdFromUrl.png?raw=true)

4. To retrieve the original URL, send a GET request to `http://localhost:8089/url/{id}`, with id being the shortened URL (remember to delete the body!).

![getUrlFromId](images/getUrlFromId.png?raw=true)


## Running the tests 🧪

At the root of the directory, run:
```
mvn test
```
