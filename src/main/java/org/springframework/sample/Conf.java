package org.springframework.sample;

import brave.http.HttpTracing;
import brave.instrumentation.awsv2.AwsSdkTracing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
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
    public SsmAsyncClient ssmClient(HttpTracing httpTracing)
    {
        AwsSdkTracing awsSdkTracing = AwsSdkTracing.create(httpTracing);

        ClientOverrideConfiguration configuration = ClientOverrideConfiguration.builder()
                                                                               .addExecutionInterceptor(awsSdkTracing.executionInterceptor())
                                                                               .build();

        return SsmAsyncClient.builder()
                             .region(Region.US_EAST_1)
                             .overrideConfiguration(configuration)
                             .build();
    }

}
