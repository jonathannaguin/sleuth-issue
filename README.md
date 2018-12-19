# sleuth-issue
Test repository that shows an issue found in Spring Sleuth when using Spring Flux and the async AWS SDK:

Run the spring boot application using IDEA for example and the perform the following CURL command:

#### Query

```
curl -vv -X POST http://localhost:8080/query
```

The output on the server will be something like, notice that we are missing the traceId and spanId in some lines:

```
2018-12-19 11:52:27.738 DEBUG [-,12eb13dd704d6c66,12eb13dd704d6c66,false] 4550 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Query request received
2018-12-19 11:52:27.738 DEBUG [-,12eb13dd704d6c66,12eb13dd704d6c66,false] 4550 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Performing query, mode 1
2018-12-19 11:52:29.019  WARN [-,,,] 4550 --- [nc-response-0-0] org.springframework.sample.Controller    : Error happened software.amazon.awssdk.services.ssm.model.ParameterNotFoundException: null (Service: Ssm, Status Code: 400, Request ID: 37dcbbf7-9a76-4d19-932b-f09747949f45)
2018-12-19 11:52:29.073 DEBUG [-,,,] 4550 --- [nc-response-0-0] org.springframework.sample.Controller    : Request handled in 1335 milliseconds```
```