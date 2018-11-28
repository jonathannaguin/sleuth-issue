package org.springframework.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Controller
public class Controller
{
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private Service service;

    @PostMapping(value = "/query")
    public Mono<ResponseEntity<Html>> q(@RequestParam(required = false, defaultValue = "0", value = "mode") int mode)
    {
        logger.debug("Query request received");

        StopWatch watch = new StopWatch();
        watch.start();

        return service.query(mode)
                      .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                      .doFinally((signalType) -> {
                          watch.stop();
                          logger.debug("Request handled in {} milliseconds", watch.getTotalTimeMillis());
                      });
    }
}
