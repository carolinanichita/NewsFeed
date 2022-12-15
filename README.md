# NewsFeed
Spring Boot Application that polls a news website for recent news and updates the database accordingly. It also exposes client APIs.

This Spring Boot application is WIP.

It is reading the last 10 news items from a news website every 5 minutes, 
and keeps the database up-to-date with the last 10 news items.

It also exposes APIs to fetch news items from the database. 
You can see the different endpoint responses by running the application and querying the endpoint from the browser, e.g.:
- http://localhost:8080/newsfeed/last10
- http://localhost:8080/newsfeed/last
- http://localhost:8080/newsfeed/search/10

Currently, the implementation is currently not efficient, especially if the number of news items that we'd like to store would be > 10.
It fetches every time the last 10 news items, and then updates the database directly.

Next steps:
- verify correctness of initializing the database
- make the data fetching and updating of the database efficient
- add unit tests
- refactor the code, especially NewsFetcher class
- improve the parsing of the xml file, i.e. properly extract description, image
- proper error handling for all the methods
- printing user-friendly messages for invalid inputs, e.g. http://localhost:8080/newsfeed/search/0
