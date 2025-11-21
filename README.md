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
        "shortUrl": "you will recieve your short url in the response"
    }

2. URL redirection

    GET api/v1/urls/{shortUlr}

    redirect to the original url

    Response body:

    ```
   {
        "longUrl": "your long url here"
    }
