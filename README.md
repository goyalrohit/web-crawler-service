# Web Crawler Service

The Web Crawler Service is a Java application that provides web crawling functionality. It allows you to crawl websites and retrieve data from web pages. The service is designed to be scalable and efficient, utilizing multi-threading and asynchronous communication.

# Features

Async Crawling: The service has a client to asynchronously communicate with the web crawler service as an approach to crawl URLs concurrently that are within the same domain, improving performance and reducing crawling time.

Real-time Communication: The service establishes a WebSocket connection with clients, enabling real-time communication and data transfer without significant delays.

Configurable Crawling Depth: You can configure the maximum depth to which the crawler should traverse the website, allowing you to control the scope of the crawling process.

Extensible Architecture: The service is designed with an extensible architecture, making it easy to add new features or integrate with other systems.


# Sequence Diagram : 

Client      --->            WebCrawlerService        ---->          Requested Website
 

![image](https://github.com/goyalrohit/web-crawler-service/assets/4389889/88f5fa06-6b37-4e34-aecd-98024ae42858)


# Technologies Used

Java

Spring Boot

Jsoup (HTML parsing library)

Rest APIs

RestTemplate

# Getting Started

Prerequisites
Java Development Kit (JDK) installed

Apache Maven installed

An IDE (e.g., IntelliJ IDEA, Eclipse)

# Building the Project

Clone the repository: 
git clone https://github.com/goyalrohit/web-crawler-service.git

Navigate to the project directory: 
cd web-crawler-service

Build the project using Maven: 
mvn clean install

# Running the Service
Start the server: 

The service will be accessible at 
http://localhost:8081

# Using the Service

Make sure the client is setup to communicate with the server -

https://github.com/goyalrohit/web-crawler-client

One the setup is setup done, Hit the URL in Postman or any webBrowser - http://localhost:8082/sitemap?url=http://www.redhat.com

The service will start crawling the website, and the retrieved data will be displayed in real-time on the client application command line

# Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

# License
This project is licensed under the MIT License.

# Contact

For any questions or support, please contact me (project maintainer) at [irohitgoyal@gmail.com].
