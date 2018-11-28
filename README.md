# sleuth-issue
Test repository that shows an issue found in Spring Sleuth when using Spring Flux

Run the spring boot application using IDEA for example and the perform the following CURL commands:

#### Success query

```
curl -vv -X POST http://localhost:8080/query
```

The output on the server will be something like, notice that we are missing the traceId and spanId in some lines:

```
2018-11-28 17:04:54.735 DEBUG [-,2cee7cfa47e006a3,2cee7cfa47e006a3,false] 10658 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Query request received
2018-11-28 17:04:54.736 DEBUG [-,2cee7cfa47e006a3,2cee7cfa47e006a3,false] 10658 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Performing query, mode 0
2018-11-28 17:04:54.741 DEBUG [-,2cee7cfa47e006a3,2cee7cfa47e006a3,false] 10658 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Performing query against https://google.com
2018-11-28 17:04:55.668 DEBUG [-,,,] 10658 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Success!
2018-11-28 17:04:55.709 DEBUG [-,,,] 10658 --- [ctor-http-nio-2] org.springframework.sample.Controller    : Request handled in 973 milliseconds
```


#### Fail query

```
curl -vv -X POST http://localhost:8080/query?mode=1
```

The output on the server will be now something like, notice that every line contains a traceId and a spanId set:

```
2018-11-28 17:07:13.434 DEBUG [-,e80db99561823af6,e80db99561823af6,false] 10658 --- [ctor-http-nio-3] org.springframework.sample.Controller    : Query request received
2018-11-28 17:07:13.434 DEBUG [-,e80db99561823af6,e80db99561823af6,false] 10658 --- [ctor-http-nio-3] org.springframework.sample.Controller    : Performing query, mode 1
2018-11-28 17:07:13.434 DEBUG [-,e80db99561823af6,e80db99561823af6,false] 10658 --- [ctor-http-nio-3] org.springframework.sample.Controller    : Performing query against https://doesnotexist.noop
2018-11-28 17:07:13.437  WARN [-,e80db99561823af6,e80db99561823af6,false] 10658 --- [ctor-http-nio-3] org.springframework.sample.Controller    : Error happened doesnotexist.noop: nodename nor servname provided, or not known
2018-11-28 17:07:13.439 DEBUG [-,e80db99561823af6,e80db99561823af6,false] 10658 --- [ctor-http-nio-3] org.springframework.sample.Controller    : Request handled in 5 milliseconds
```
