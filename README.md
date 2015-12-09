# java-lenddo v0.0.1

Java-Lenddo is a Java SDK for getting Lenddo's ClientSCore and ClientVerification. Currently it only supports Synchronous or blocking calls.

### Usage
1) Add the LenddoApi.jar to your Java project as library. Get the jar file from the releases. <link here>
2) Initialize the LenddoApi object by supplying the provided api_key and api_secret.
```Java
LenddoApi lenddoapi = new LenddoApi(api_key, api_secret);
```
3) To get a ClientScore, call the getClientScoreAsString(clientId) and provide the client_id as parameter. This will return a JSON response in String.
```Java
lenddoapi.getClientScoreAsString("client_id");
```
4) To get a ClientVerification, call the getClientVerificationAsString(clientId) and provide the client_id as parameter. This will return a JSON response in String.
```Java
lenddoapi.getClientScoreAsString("client_id");
```

### Version
v0.0.1  - (12/09/2015) First Cut

### Changelogs
v0.0.1  - (12/09/2015) Initial version

### Todos

- Code Comments and JavaDocs
- Support for Asynchronous Calls and Callbacks
- Error Handling
- Whitelabel Scoring
- Webhooks support
- Unit Testing

License
----

MIT




JMA