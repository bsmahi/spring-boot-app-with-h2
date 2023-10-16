# Build Spring Boot CRUD Application with H2 Database

In this application, we are building a simple CRUD application with h2 for products.
Initially, we shall develop a basic web application utilizing Spring Boot, without resorting to Docker. Subsequently, we
shall construct a Docker image exclusively for the aforementioned application. Let’s get started.

## Pre-requisites

- [Java 17](https://www.oracle.com/java/technologies/downloads/)
- [Spring Boot CLI](https://docs.spring.io/spring-boot/docs/1.0.2.RELEASE/reference/html/getting-started-installing-spring-boot.html) (
  optional)
- [IntelliJ IDEA](https://www.jetbrains.com/edu-products/download/#section=idea)
- [Docker Desktop](https://docs.docker.com/desktop/?_gl=1*1sq9eto*_ga*MTMzMDc1Mjc4OC4xNjk2NTYwMzgy*_ga_XJWPQMJYHQ*MTY5NzQ2NjQwOS40LjEuMTY5NzQ2Nzg4Mi41OS4wLjA)

## Getting Started

After the successful installation of the tools on your system, please proceed to adhere to the following instructions in
order to construct a basic web application utilizing Spring Boot.

## Starting with Spring Initializr

[Spring Initializr](https://start.spring.io/) is a rapid initiation tool for Spring projects. It offers a flexible
application programming
interface (API) for generating projects based on the Java Virtual Machine (JVM), incorporating various fundamental
language generation options such as Java, Kotlin, and Groovy. Moreover, Spring Initializr facilitates the abstraction of
build systems by providing support for Apache Maven and Gradle implementations.
Furthermore, it exposes web endpoints that enable the generation of tangible projects and the provision of their
metadata in a widely recognized format. This
feature allows third-party clients to offer assistance in areas where it is required.

Open
this [pre-initialized project](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=com.springapp&artifactId=spring-boot-app-with-h2&name=spring-boot-app-with-h2&description=Demo%20project%20for%20Spring%20Boot&packageName=com.springapp.spring-boot-app-with-h2&dependencies=web,data-jpa,h2,lombok)
in order to generate a ZIP file. Here’s how that looks:

For the purpose of this demonstration, we have combined Maven build automation with Java, a Spring Web, Spring Data Jpa,
H2 database, Lombok dependencies, and Java 17 for our metadata.

![Image](./images/springinitalizer.png "Spring Initalizer")

Click “Generate” to download **“spring-boot-app-with-h2.zip”**. Use the unzip command to extract your files.

## Project Structure

Once you unzip the file, you’ll see the following project directory structure:





