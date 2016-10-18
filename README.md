![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v2.1.0

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ApplicationScore and ApplicationVerification. Usage is very simple and straightforward. This supports Asynchronous calls and will return a POJO. It is possible to get a JSON String from the response using a provided utility method.

### Usage
1) Download the [Jar file](https://github.com/Lenddo/java-lenddo/releases/download/v2.1.0/LenddoApi.zip) and add the LenddoApi.jar to your Java project as library.

2) Initialize the LenddoScoreApi object by supplying the provided api\_key, api\_secret and partner\_script_id Strings.

```java
        // Required imports
        import com.lenddo.javaapi.LenddoScoreApi;
        import com.lenddo.javaapi.LenddoApiCallback;
        import com.lenddo.javaapi.models.ClientScore;
        import com.lenddo.javaapi.models.ClientVerification;
        import com.lenddo.javaapi.utils.ApiUtils;

        ...

        // Provide your credentials here
        String api_key = "YOUR API KEY";
        String api_secret = "YOUR API SECRET";
        String partner_script_id = "YOUR PARTNER SCRIPT ID";
        
        // Initialize the LenddoScoreApi object
        LenddoScoreApi lenddoapi = new LenddoScoreApi(api_key, api_secret, partner_script_id);
```

3) To get an ApplicationScore, call the getApplicationScore(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.

```java
        lenddoapi.getApplicationScore("YOUR_APPLICATION_ID", new LenddoApiCallback<ClientScore>() {
            @Override
            public void onResponse(ClientScore response) {
                System.out.println("ApplicationScore: "+ ApiUtils.convertObjectToJsonString(response));
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

### WhiteLabel Client Api
The WhiteLabelApi will allow you to utilize Lenddo services without any Lenddo branding. This method of implementation is the most complex but allows you to fully customize your users' experience. WhiteLabel client api is also included in jar file, you just need to download and import the java jar file, as stated at the above instructions.
The Lenddo WhiteLabel client api provides two primary functions, sending a network token, and sending an application, but first we need to initialize some key components to get started.

* Initialization
```java
     // Required imports
        import com.lenddo.javaapi.WhiteLabelApi;
        import com.lenddo.javaapi.LenddoApiCallback;
        import com.lenddo.javaapi.models.PartnerToken;
        import com.lenddo.javaapi.models.CommitPartnerJob;
        import com.lenddo.javaapi.utils.ApiUtils;
        
     // Provide your credentials here
        String api_key = "YOUR API KEY";
        String api_secret = "YOUR API SECRET";
        String partner_script_id = "YOUR PARTNER SCRIPT ID";
        
     // Initialize the WhiteLabelApi object
        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(api_key, api_secret, partner_script_id);
```

1) PartnerToken call which will allow you to send social network oauth tokens to Lenddo. These tokens will be used in the second step to provide scoring services for your client. This call returns a profile_id which you will be required to save so that you can send it to use for the second call.

```java
        WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td = new WhitelabelRequestBody.WLPartnerTokenRqBody.token_data();
        td.key = "YOUR_TOKEN";

        String provider = WhiteLabelApi.PROVIDER_FACEBOOK;
        whiteLabelApi.postPartnerToken("YOUR_APPLICATION_ID", provider, td, new LenddoApiCallback<PartnerToken>() {
            @Override
            public void onResponse(PartnerToken response) {
                System.out.println("PartnerToken: " + ApiUtils.convertObjectToJsonString(response));
                System.out.println("profile_id: " +  ApiUtils.convertObjectToJsonString(response.profile_id));
                // get the profile ids from the response and use postCommitPartnerJob() to send the profile ids.
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: " + t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: " + errormessage);
            }
        });
```

2) CommitPartnerJob service call creates a job for scoring based on the a one time use id (known as the APPLICATION_ID), a list of profile_ids which you gathered from the first service call, and finally a partner_script_id which dictates how Lenddo will inform you of the results.

```java

        JsonArray profile_ids = new JsonArray();
        profile_ids.add("the resulting partner id from callback of postPartnerToken()");
        Verification verification = new Verification();
    
        whiteLabelApi.postCommitPartnerJob("YOUR_APPLICATION_ID", profile_ids, verification, new LenddoApiCallback<CommitPartnerJob>() {
            @Override
            public void onResponse(CommitPartnerJob response) {
                System.out.println("CommitPartnerJob:"+ ApiUtils.convertObjectToJsonString(response));
            }
    
            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: "+t.getMessage());
            }
    
            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+errormessage);
            }
        });

```

### Release Version
[**v2.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.1.0).  - (10/12/2016) WhiteLabel Client

[**v2.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.0.0).  - (10/12/2016) major update

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
