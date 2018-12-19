package org.springframework.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class Service
{
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private Client client;

    @Autowired
    private ModeService modeService;

    public Mono<Html> query(int mode)
    {
        logger.debug("Performing query, mode {}", mode);

        Mono<String> urlMono = modeService.find(mode);

        return urlMono.flatMap(url -> client.execute(url))
                       .flatMap(contents -> {
                           logger.debug("Success!");
                           return Mono.just(new Html(contents));
                       })
                       .onErrorResume(error -> Mono.just(new ErrorHandler().handle(error)));
    }
}
