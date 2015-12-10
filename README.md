![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v0.0.1

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ClientScore and ClientVerification. Usage is very simple and straightforward. Currently it only supports Synchronous or blocking calls. 

### Usage
1) Download the [Jar file](https://github.com/Lenddo/java-lenddo/releases/download/v0.0.1/LenddoApi.zip) and add the LenddoApi.jar to your Java project as library.

2) Initialize the LenddoApi object by supplying the provided api_key and api_secret Strings.
```java
LenddoApi lenddoapi = new LenddoApi("api_key", "api_secret");
```
3) To get a ClientScore, call the getClientScoreAsString(clientId) method and provide the client_id as parameter. This will return a response String in JSON format.
```java
lenddoapi.getClientScoreAsString("client_id");
```
4) To get a ClientVerification, call the getClientVerificationAsString(clientId) method and provide the client_id as parameter. This will return a response String in JSON format.
```java
lenddoapi.getClientVerificationAsString("client_id");
```

### Release Version
[**v0.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v0.0.1).  - (12/09/2015) First Cut

### Changelogs
v0.0.1  - (12/09/2015) Initial version

### Todos
- Return response as POJO
- Code Comments and JavaDocs
- Support for Asynchronous Calls and Callbacks
- Error Handling
- Whitelabel Scoring
- Webhooks support
- Unit Testing

License
----

[MIT](https://raw.githubusercontent.com/Lenddo/java-lenddo/v0.0.1/LICENSE)
