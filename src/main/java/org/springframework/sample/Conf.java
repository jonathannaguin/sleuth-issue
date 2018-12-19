package org.springframework.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;

@Configuration
public class Conf
{
    @Bean
    public WebClient webClient()
    {
        return WebClient.create();
    }

    @Bean
    public SsmAsyncClient ssmClient()
    {
        return SsmAsyncClient.builder()
                             .region(Region.US_EAST_1)
                             .build();
    }

}
