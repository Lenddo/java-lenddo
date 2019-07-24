![Lenddo logo](http://cdn.alleywatch.com/wp-content/uploads/2013/11/lendo_logo.png)

# java-lenddo 
##### v2.10.1

### 
###
Java-Lenddo is a Java SDK for getting Lenddo's ApplicationScore and ApplicationVerification. Usage is very simple and straightforward. This supports Asynchronous calls and will return a POJO. It is possible to get a JSON String from the response using a provided utility method. [Click here](UIDemo.jar) for demo

Minimum required JAVA version is Java 8.

### Usage
1) Download the latest [LenddoApi.zip](https://github.com/Lenddo/java-lenddo/releases) file, extract and add the LenddoApi.jar to your Java project as a library.

2) Initialize the LenddoScoreApi object by supplying the provided api\_key, api\_secret and partner\_script_id Strings.

```java
// Required imports
import com.lenddo.javaapi.LenddoScoreApi;
import com.lenddo.javaapi.LenddoApiCallback;
import com.lenddo.javaapi.models.ApplicationScore;
import com.lenddo.javaapi.models.ApplicationVerification;
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
    lenddoapi.getApplicationScore("YOUR_APPLICATION_ID", new LenddoApiCallback<ApplicationScore>() {
        @Override
        public void onResponse(ApplicationScore response) {
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

Here is a sample raw response:

```javascript
{
	"score": 885,
	"flags": ["FLAG_1_VALUE", "FLAG_2_VALUE"],
	"application_id": "YOUR_APPLICATION_ID",
	"partner_id": "YOUR_PARTNER_ID_VALUE_HERE",
	"partner_script_id": "YOUR_PARTNER_SCRIPT_ID_VALUE_HERE",
	"created": 1519872715,
	"application_created": 1519872712
}
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
    lenddoapi.getApplicationVerification("YOUR_APPLICATION_ID", new LenddoApiCallback<ApplicationVerification>() {
        @Override
        public void onResponse(ApplicationVerification response) {
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

Create a Credentials class to hold credential information

```java
    public static class Credentials {
        public String api_key;
        public String api_secret;
        public String partner_script_id;

        Credentials (String api_key, String api_secret, String partner_script_id) {
            this.api_key = api_key;
            this.api_secret = api_secret;
            this.partner_script_id = partner_script_id;
        }
    }
```


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

11) To get the **MobileData**, call the getMobileData(applicationId, callback) method using the NetworkApi object and provide the partner_script_id, partner_id and a LenddoApiCallback object as parameter. See sample code below:


```java
    private static void sampleGetMobileData(String apiKey, String apiSecret, String psid, String partnerId) {
        NetworkApi networkApi = new NetworkApi(apiKey, apiSecret);
        networkApi.getMobileData(psid, partnerId, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println(String.valueOf(response));
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println(errormessage);
            }
        });
    }

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

### Secured Data Api
The Secured Data API utilizes application endpoints.
The project must have LenddoApi.jar, and as well as the Bouncy Castle JARs
[bckpix-jdk15on-161.jar and bcprov-jdk15on-161.jar](https://www.bouncycastle.org/latest_releases.html)
This API allows partners to retrieve their data securely through cryptography.
Every API needs the private key to be passed as a parameter in order to utilize the API.

* Importing the JAR file
In your project, click File >> Project Settings.
Go to Modules >> Dependencies
![Depencency List](https://github.com/Lenddo/java-lenddo/master/Wiki/secured_001_dependencies.PNG)

Click the + sign on the right, then choose JAR
![Add JAR](https://github.com/Lenddo/java-lenddo/master/Wiki/secured_002_add.PNG)

Select the LenddoAPI JAR. Do the same for bckpix and bcprov JAR files.
![List](https://github.com/Lenddo/java-lenddo/master/Wiki/secured_003_list.PNG)

* Initialization
The client must also have the copy of the private key generated.
The decryption key (PEM File) should exist in your local directory or network which will then be used during instantiation of the client.

To generate the key, run the following in your terminal to create the private key
```
openssl genrsa -out private.pem 2048
```
Then create a public key (this will be provided to us through your respective account manager)
```
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
```

```java
        // Required imports
       import com.google.gson.JsonArray;
       import com.google.gson.JsonElement;
       import com.google.gson.JsonObject;
       import com.lenddo.javaapi.*;
       import com.lenddo.javaapi.models.*;
       import com.lenddo.javaapi.utils.ApiUtils;

        // Provide your credentials here
        String api_key = "API KEY";
        String partner_script_id = "PARTNERSCRIPT ID";
        String private_key = "Sample\\private.pem"; // Location of your private key
        String api_secret = "API SECRET";
        String document_id = "DOCUMENT ID";

        double pageSize = 100;
        double pageNumber = 1;

        // Format YYYY-MM-dd HH:mm
        String startDate = "START DATE";
        String endDate = "END DATE";

        // Initialize
        Credentials credentials = new Credentials(api_key, api_secret, partner_script_id);

        // Test Applications API
        sampleGetApplications(credentials, private_key);
        sampleGetApplicationsWithFilter(credentials, private_key, pageSize, pageNumber, startDate, endDate);
        sampleGetApplicationDetails(credentials, private_key, application_id);
        sampleGetDocumentDetails(credentials, private_key, application_id, document_id);
```

1) **Applications** retrieves the list of applications, optional parameters are pageSize, pageNumber, startDate and endDate.

```java
        // TEST CODE FOR APPLICATIONS
            private static void sampleGetApplications(Credentials credentials, String privateKey) {
                LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
                LenddoApplicationApi.debugMode(true);
                lenddoApplicationApi.getApplications(credentials.partner_script_id, new LenddoApiCallback() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("sampleGetApplications response=" + ApiUtils.convertObjectToJsonString(response));
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

            private static void sampleGetApplicationsWithFilter(Credentials credentials,
                                                                String privateKey,
                                                                double pageSize,
                                                                double pageNumber,
                                                                String startDate,
                                                                String endDate) {
                LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
                LenddoApplicationApi.debugMode(true);
                lenddoApplicationApi.getApplicationsWithFilter(
                        credentials.partner_script_id,
                        pageSize,
                        pageNumber,
                        startDate,
                        endDate,
                        new LenddoApiCallback() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("sampleGetApplicationsWithFilter response=" + ApiUtils.convertObjectToJsonString(response));
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

Here is a sample raw response:

```javascript
{
	"application_ids": ["TESTjqrgls14", "TESTjqru18mu", ...],
	"data_count": 412,
	"page_count": 5
}
```

2) **Application Details** retrieves the list of details of an application, given its applicationId.

```java
         private static void sampleGetApplicationDetails(Credentials credentials, String privateKey, String applicationId) {
                LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
                LenddoApplicationApi.debugMode(true);
                lenddoApplicationApi.getApplicationDetails(credentials.partner_script_id, applicationId, new LenddoApiCallback() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("sampleGetApplicationDetails response="+ ApiUtils.convertObjectToJsonString(response));
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

Here is a sample raw response:

```javascript
{
	"verification_data": {
		"name": {
			"first": "A",
			"middle": "B",
			"last": "B"
		},
		"date_of_birth": "1989-01-02",
		"email": "a@test.com",
		"work_email": "a@test.com",
		"phone": {
			"mobile": "+639178012121",
			"home": null
		},
		"employer": null,
		"employment_period": {
			"start_date": null,
			"end_date": null
		},
		"mothers_maiden_name": {
			"first": null,
			"middle": null,
			"last": null
		},
		"university": null,
		"address": {
			"line_1": null,
			"line_2": null,
			"city": null,
			"administrative_division": null,
			...
		},
		"work_address": {
			"line_1": null,
			"line_2": null,
			"city": null,
			...
		}
	},
	"partner_data": {
		"name": {
			"first": "A",
			"middle": "B",
			"last": "B"
		},
		"birthdate": {
			"day": "02",
			"month": "01",
			"year": "1989"
		},
		"email": "a@test.com",
		"work": "a@test.com",
		"mobile_phone": "+639178012121"
	},
	"verification_results": {},
	"documents_submitted": [],
	"documents_pending": []
}
```

3) *Document Details* retrives the details of a document given its applicationId and documentId.

```java
        private static void sampleGetDocumentDetails(Credentials credentials, String privateKey, String applicationId, String documentId) {
                LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
                LenddoApplicationApi.debugMode(true);
                lenddoApplicationApi.getDocumentDetails(credentials.partner_script_id, applicationId, documentId, new LenddoApiCallback() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("sampleGetDocumentDetails response="+ ApiUtils.convertObjectToJsonString(response));
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

Here is a sample raw response:

```javascript
{
	"content": {
		"format": "jpg",
		"value": "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAA..."
	}
}
```

### Release Version
[**v2.10.1**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.10.0).  - (07/24/2018) Add Secured Data API

[**v2.9.0**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.9.0).  - (05/16/2018) Add MobileData API call using Network Service

[**v2.8.2**](https://github.com/Lenddo/java-lenddo/releases/tag/v2.8.2).  - (03/08/2018) Update endpoints

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
v2.10.0 -- (07/02/2018) Add Secured Data API
- Add Secured Data API

v2.9.0  -- (05/16/2018) Add MobileData API call using Network Service
- Added /MobileData Api call

v2.8.2  -- (03/08/2018) Update endpoints
- update to ApplicationScore and ApplicationVerification endpoints

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
