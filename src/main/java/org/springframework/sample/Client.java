package org.springframework.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Client
{
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Mono<String> execute(int mode)
    {
        String url;

        if (mode == 1)
        {
            url = "https://doesnotexist.noop";
        }
        else
        {
            url = "https://google.com";
        }

        WebClient webClient = WebClient.create(url);

        logger.debug("Performing query against {}", url);
        return webClient.method(HttpMethod.GET)
                        .retrieve()
                        .bodyToMono(String.class);
    }
}
