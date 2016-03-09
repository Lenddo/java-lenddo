![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v1.0.1

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ClientScore and ClientVerification. Usage is very simple and straightforward. This supports Synchronous and Asynchronous calls. 

### Usage
1) Download the [Jar file](https://github.com/Lenddo/java-lenddo/releases/download/v1.0.1/LenddoApi.zip) and add the LenddoApi.jar to your Java project as library.

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
[**v1.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.1).  - (03/09/2016) security update

[**v1.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.0).  - (01/15/2016) Initial Release

[**v0.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v0.0.1).  - (12/09/2015) First Cut

### Changelogs
v1.0.1  -- (03/09/2016) security update
- Fixed SSLHandshakeException
- Updated to use TLSv1.2 and latest Ciphers for security
- Updated Retrofit2 and OkHttp3 libraries


v1.0.0  -- (01/15/2016) Initial Release
- Return response as POJO
- Added and fixed code comments 
- Support for Asynchronous Calls and Callbacks
- Error Handling Support with async calls
- added some unit tests
- Updated to use latest library versions for OkHttp, Retrofit, GsonConverter
- Fixed ClientScore and ClientVerification class mapping to actual json results

        
v0.0.1  - (12/09/2015) Initial version


License
----

[MIT](https://raw.githubusercontent.com/Lenddo/java-lenddo/v1.0.0/LICENSE)
