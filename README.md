# Scalable Url Shortener

I am going to document all the steps that i take while building this application.

first let's talk about all the requirements that i expect from this project.
- URL Shortening: users should be able to input a long url and receive a short unique url in return.
- URL redirection: When users access a shortened URL, the service should redirect them seamlessly to the original URL with minimal delay.

some other things that i want to talk about - to be implemented in the future.
- the application should have some feature to let user delete their short urls from the database, although for this i will need the users to create their accounts first, so for now i am sticking with time based deletion or maybe no deletion at all.
- let users create custom urls(if available).
- analytics to measure site engagement.

let's talk about scale requirements
- daily active users: 100M
- read:write ratio: 100:1
- write requests per day: 1M
- assuming that each entry is 500 bytes.

## API Endpoints:

1. URL shortening endpoint:
    
    POST api/v1/shorten

    This endpoint accepts a long URL and returns a shortened version.
    
    Request body:
    ```txt
    {
        "longUrl": "your long url here"
    }
   ```

   Request body:
    ```txt
    {
        "shortUrl": "you will recieve your short url in the response",
        "longUrl": "the original long url that you posted"
    }

2. URL redirection

    GET api/v1/urls/{shortUlr}

    redirect to the original url

    Response body:

    ```
   {
        "longUrl": "your long url here"
    }

## High Level Design

### URL shortening
The url shortening service generates a unique short url for the long url provided by the user.

the high level diagram has three main components:
![urlShorteningHLD.png](src/main/resources/static/urlShorteningHLD.png)

- **user**: the frontend application sends a POST request to the server with the long url to fetch the shortened url.
- **url shortening service**: the system receives the request and it generates the short url in the following steps:
    - generate a unique short url(will discuss the techniques later).
    - store the short url - long url map in the database.
- **database**: use a database for storing the short url - long url mapping.

### URL redirection
The url redirection service returns the original long url using the short url.

the high level diagram has four main components:
![urlRedirectionHLD.png](src/main/resources/static/urlRedirectionHLD.png)

- **user**: the frontend application sends a GET request to the server to fetch the original url.
- **url redirection service**: the system receives the request and goes through the following procedure to generate a response:
    - first the service checks the cache for the long url, if it finds it then return the long url but if not then check the database
    - if the database has an entry then return the original long url but if not then throw an error.
- **cache**: we implement this layer to reduce latency and offload requests from the database, this is an in-memory store and is much faster compared to disk-based databases.
- **database**: in case the url is not found in the cache, then look for it in the database.

## High level deep dive:
