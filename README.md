![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v2.0.0

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ApplicationScore and ApplicationVerification. Usage is very simple and straightforward. This supports Asynchronous calls and will return a POJO. It is possible to get a JSON String from the response using a provided utility method.

### Usage
1) Download the [Jar file](https://github.com/Lenddo/java-lenddo/releases/download/v1.0.2/LenddoApi.zip) and add the LenddoApi.jar to your Java project as library.

2) Initialize the LenddoApi object by supplying the provided api\_key, api\_secret and partner\_script_id Strings.

```java
        // Required imports
        import com.lenddo.javaapi.LenddoApi;
        import com.lenddo.javaapi.LenddoApiCallback;
        import com.lenddo.javaapi.models.ClientScore;
        import com.lenddo.javaapi.models.ClientVerification;

        ...

        // Provide your credentials here
        String api_key = "YOUR API KEY";
        String api_secret = "YOUR API SECRET";
        String partner_script_id = "YOUR PARTNER SCRIPT ID";
        
        // Initialize the LenddoApi object
        LenddoApi lenddoapi = new LenddoApi(api_key, api_secret, partner_script_id);
```

3) To get an ApplicationScore, call the getApplicationScore(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.

```java
        lenddoapi.getApplicationScore("YOUR_APPLICATION_ID", new LenddoApiCallback<ClientScore>() {
            @Override
            public void onResponse(ClientScore response) {
                System.out.println("score: "+response.score);
                System.out.println("flags: "+response.flags);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: "+t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
        });
```

4) To get an ApplicationVerification, call the getApplicationVerification(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.


```java
          lenddoapi.getApplicationVerification("YOUR_APPLICATION_ID", new LenddoApiCallback<ClientVerification>() {
              @Override
              public void onResponse(ClientVerification response) {
                  System.out.println("ApplicationVerification: "+ ApiUtils.convertObjectToJsonString(response));
                  System.out.println("probes: "+ ApiUtils.convertObjectToJsonString(response.probes));
                  System.out.println("probe name: "+ response.probes.name);
                  System.out.println("probe firstname: "+ response.probes.name.get(0));
              }

              @Override
              public void onFailure(Throwable t) {
                  System.out.println("Connection Failure: "+t.getMessage());
              }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
          });
```

5) To convert the response object to a JSON String, call the ApiUtils.convertObjectToJsonString(object) method.

```java
          String jsonstring = ApiUtils.convertObjectToJsonString(response);
```

### Release Version
[**v2.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.2).  - (10/12/2016) major update

[**v1.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.1).  - (03/09/2016) security update

[**v1.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.0).  - (01/15/2016) Initial Release

[**v0.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v0.0.1).  - (12/09/2015) First Cut

### Changelogs
v2.0.0  -- (10/12/2016) major update
- Updated OkHTTP3 and Retrofit2 libraries
- Removed legacy support and updated to latest api call
- Calls are now asynchronous and returns POJO
- ClientId is now ApplicationId
- ClientScore is now ApplicationScore
- ClientVerification is now ApplicationVerification

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
