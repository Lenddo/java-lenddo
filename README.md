![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v2.8.1

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ApplicationScore and ApplicationVerification. Usage is very simple and straightforward. This supports Asynchronous calls and will return a POJO. It is possible to get a JSON String from the response using a provided utility method. [Click here](UIDemo.jar) for demo

### Usage
1) Download the latest [LenddoApi.zip](https://github.com/Lenddo/java-lenddo/releases) file, extract and add the LenddoApi.jar to your Java project as a library.

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

The LenddoScoreApi object can use a different hostname by supplying the hostname in the Constructor.

```java
    // Initialize the LenddoScoreApi object with a different hostname
    String hostname = "https://scoreservice-va.lenddo.com"; // sample regional hostname
    LenddoScoreApi lenddoapi = new LenddoScoreApi(api_key, api_secret, partner_script_id, hostname);
```


3) To get an **ApplicationScore**, call the getApplicationScore(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.

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

4) To get **ApplicationScorecards**, call the getApplicationScore(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.

```java
    lenddoapi.getApplicationScorecards("YOUR_APPLICATION_ID", new LenddoApiCallback<ApplicationScorecards>() {
        @Override
        public void onResponse(ApplicationScorecards response) {
            System.out.println("ApplicationScorecards: "+ ApiUtils.convertObjectToJsonString(response));
            System.out.println("score: "+response.scorecards);
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


5) To get an **ApplicationVerification**, call the getApplicationVerification(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.


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

6) To get the **ApplicationFeatures**, call the getApplicationFeatures(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.


```java
    lenddoapi.getApplicationFeatures("YOUR_APPLICATION_ID", new LenddoApiCallback<ApplicationFeatures>() {
        @Override
        public void onResponse(ApplicationFeatures response) {
            System.out.println("ApplicationFeatures: "+ ApiUtils.convertObjectToJsonString(response));
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

7) To post **ExtraApplicationData**, call the postExtraApplicationData(applicationId, extraData, callback) method and provide the application_id, extraData as a JsonObject and a LenddoApiCallback object as parameter. Your partnerscript must enable the feature for sending extra data.


```java
    // TEST CODE FOR SEND EXTRA PARTNER DATA
    private static void samplePostExtraPartnerData(Credentials credentials, String applicationId, JsonObject extraData) {
        NetworkApi networkApi = new NetworkApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        NetworkApi.debugMode(true);
        networkApi.postExtraApplicationData(applicationId, extraData, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response="+ ApiUtils.convertObjectToJsonString(response));
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
    }
```

8) To get the **ApplicationMultipleScores**, call the getApplicationMultipleScores(applicationId, callback) method and provide the application_id and a LenddoApiCallback object as parameter.


```java
    lenddoapi.getApplicationMultipleScores("YOUR_APPLICATION_ID", new LenddoApiCallback<ApplicationMultipleScores>() {
        @Override
        public void onResponse(ApplicationMultipleScores response) {
            System.out.println("ApplicationMultipleScores: "+ ApiUtils.convertObjectToJsonString(response));
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

9) To post **PriorityData**, create an AuthorizeApi object and call the postAuthorizeOnboardingPrioritydata(applicationId, body, callback) method and provide the application_id, body as a PriorityDataRequestBody and a LenddoApiCallback object as parameter.


```java
    // TEST CODE FOR POST AUTHORIZE ONBOARDING PRIORITYDATA
    private static void samplePostAuthorizeOnboardingPriorityData(Credentials credentials, String applicationId, PriorityDataRequestBody body) {
        AuthorizeApi authorizeApi = new AuthorizeApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        AuthorizeApi.debugMode(true);
        if (body == null) {
            body = new PriorityDataRequestBody();
        }
        authorizeApi.postAuthorizeOnboardingPrioritydata(applicationId, body, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response="+ ApiUtils.convertObjectToJsonString(response));
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
    }
```

Calling the above sample method:

```java
    // Initializing your credentials.
    String api_key = "YOUR_API_KEY";
    String api_secret = "YOUR_API_SECRET";
    String partner_script_id = "YOUR_PARNER_SCRIPT_ID";
    String applicationId = "YOUR_UNIQUE_APPLICATION_ID";
    Credentials credentials = new Credentials(api_key, api_secret, partner_script_id);
    
    // Creating a PriorityData Request Body
    PriorityDataRequestBody body = new PriorityDataRequestBody();
    body.application_id = applicationId;
    body.partner_script_id = partner_script_id;
    body.data.partner_data = new JsonObject();               // Store partner data here as a JsonObject
    body.data.verification_data.name.first = "FIRSTNAME";    // First name field, optional
    body.data.verification_data.name.middle = "MIDDLENAME";  // Middle name field, optional
    body.data.verification_data.name.last = "LASTNAME";      // Last name field, optional
    body.data.verification_data.employer = "EMPLOYER";       // Employer field. optional
    body.data.verification_data.email = "EMAIL";             // Email field. optional
    body.data.verification_data.university = "UNIVERSITY";   // University field. optional
    // etc...

    samplePostAuthorizeOnboardingPriorityData(credentials, applicationId, body);    
```


10) To convert the response object to a JSON String, call the ApiUtils.convertObjectToJsonString(object) method.

```java
    // Convert Object to Json String and filter out null values
    String jsonstring = ApiUtils.convertObjectToJsonString(response);
    // Convert Object to Json String and filter out null values
    String jsonstring = ApiUtils.convertObjectToJsonStringNoNulls(response);
```

### WhiteLabel Client Api
The WhiteLabelApi will allow you to utilize Lenddo services without any Lenddo branding. This method of implementation is the most complex but allows you to fully customize your users' experience. WhiteLabel client api is also included in a jar file, you just need to download and import the java jar file, as stated at the above instructions.
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

1) **PartnerToken** call which will allow you to send social network oauth tokens to Lenddo. These tokens will be used in the second step to provide scoring services for your client. This call returns a profile_id which you will be required to save so that you can send it to use for the second call.

```java
    // TEST CODE FOR PARTNERTOKEN API
    private static void samplePostPartnerToken(final Credentials credentials, final String applicationId, String provider) {
        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td = new WhitelabelRequestBody.WLPartnerTokenRqBody.token_data();
        // add a token in the td.key and a secret in td.secret
        td.key = "PUT YOUR TOKEN HERE";
        td.secret = "PUT A SECRET HERE IF APPLICABLE";
        whiteLabelApi.postPartnerToken(applicationId, provider, td, new LenddoApiCallback<PartnerToken>() {
            @Override
            public void onResponse(PartnerToken response) {
                System.out.println("response="+ response.profile_id);
                // get the profile ids from the response and use postCommitPartnerJob() to send the profile ids.
                samplePostCommitPartnerJob(credentials, applicationId, response.profile_id);
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
    }
```

2) **CommitPartnerJob** service call creates a job for scoring based on the a one time use id (known as the APPLICATION_ID), a list of profile_ids which you gathered from the first service call, and finally a partner_script_id which dictates how Lenddo will inform you of the results.

```java

// TEST CODE FOR COMMITPARTNERJOB API
    private static void samplePostCommitPartnerJob(Credentials credentials, String applicationId, String profileId) {
        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        JsonArray profile_ids = new JsonArray();
        profile_ids.add(profileId);
        Verification verification = new Verification();
        // at this point, you need to add details for the verification object. (name, employer, etc).
        verification.name.first="firstname";
        verification.name.last="lastname";
        
        whiteLabelApi.postCommitPartnerJob(applicationId, profile_ids, verification, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response="+ ApiUtils.convertObjectToJsonString(response));
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
    }
```

### Release Version
[**v2.8.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.8.1).  - (12/19/2017) Fix bug with DataConverterType class

[**v2.8.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.8.0).  - (08/17/2017) Add support for PriorityData endpoint

[**v2.7.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.7.0).  - (07/24/2017) Add support for ApplicationMultipleScores endpoint

[**v2.6.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.6.0).  - (07/14/2017) Add support for ExtraApplicationData endpoint

[**v2.5.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.5.0).  - (06/26/2017) Add support for ApplicationFeatures endpoint

[**v2.4.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.4.0).  - (04/05/2017) Fix Date Header issue with Locale

[**v2.3.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.3.0).  - (03/17/2017) Dynamic Score API hostname

[**v2.2.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.2.0).  - (03/10/2017) Add support for ApplicationScorecards endpoint

[**v2.1.4**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.1.4).  - (01/24/2017) Update Verification and Probe Endpoint

[**v2.1.3**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.1.3).  - (01/24/2017) Update Verification Endpoint

[**v2.1.2**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.1.2).  - (11/14/2016) WhiteLabel Client fix

[**v2.1.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.1.1).  - (10/20/2016) WhiteLabel Client

[**v2.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.0.0).  - (10/12/2016) major update

[**v1.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.1).  - (03/09/2016) security update

[**v1.0.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v1.0.0).  - (01/15/2016) Initial Release

[**v0.0.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v0.0.1).  - (12/09/2015) First Cut

### Changelogs
v2.8.1  -- (12/19/2017) Fix bug with DataConverterType class
- Replaced DataConverterType class with Java.util.Base64

v2.8.0  -- (08/17/2017) Add support for PriorityData endpoint
- Added PriorityData endpoint support
- Fixed internal Date header generation
- Updated Verification data model
- Added API call to Authorize-v3 endpoint

v2.7.0  -- (07/24/2017) Add support for ApplicationMultipleScores endpoint
- Added ApplicationMultipleScores endpoint support

v2.6.0  -- (07/14/2017) Add support for ExtraApplicationData endpoint
- Added ExtraApplicationData endpoint support

v2.5.0  -- (06/26/2017) Add support for ApplicationFeatures endpoint
- Added ApplicationFeatures endpoint support

v2.4.0  -- (03/15/2017) Fix Date Header issue with Locale
- Force US Locale with Date header creation

v2.3.0  -- (03/15/2017) Dynamic Score API hostname
- Support API call to different hostname

v2.2.0  -- (03/10/2017) Add support for ApplicationScorecards endpoint
- Updated gradle dependencies
- Updated ClientScore response body
- Updated Log output to be more discreet

v2.1.4  -- (01/24/2017) Update Verification and Probe Endpoint
- Added application_created
- Added verifications.phone
- Added verifications.email
- Added probes.phone
- Added probes.email
- Added duplicate_profiles
- Renamed _id to application_id

v2.1.3  -- (01/24/2017) Update Verification Endpoint
- Remove probes.facebook_verified
- Remove verifications.facebook_verified
- Add to root verified_by_facebook boolean flag

v2.1.2  -- (11/14/2016) Whitelabel Client
- Fix bug in postCommitPartnerJob API Call resulting to forbidden error

v2.1.1  -- (10/20/2016) Whitelabel Client
- Add Whitelabel client support
- PartnerToken API Call
- CommitPartnerJob API Call

v2.0.0  -- (10/20/2016) major update
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
