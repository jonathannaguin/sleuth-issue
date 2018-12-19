package org.springframework.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Client
{
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private WebClient webClient;

    public Mono<String> execute(String url)
    {
        logger.debug("Performing query against {}", url);
        return webClient.method(HttpMethod.GET)
                        .uri(url)
                        .retrieve()
                        .bodyToMono(String.class);
    }
}
