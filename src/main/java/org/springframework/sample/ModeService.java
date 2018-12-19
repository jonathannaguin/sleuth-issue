package org.springframework.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.ssm.SsmAsyncClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Service
public class ModeService
{
    @Autowired
    private SsmAsyncClient ssmClient;

    public Mono<String> find(int mode)
    {
        String names = "url.to.use." + mode;

        GetParameterRequest request = GetParameterRequest.builder()
                                                         .withDecryption(true)
                                                         .name(names)
                                                         .build();

        return Mono.fromFuture(ssmClient.getParameter(request))
                   .flatMap(getParameterResponse -> Mono.just(getParameterResponse.parameter()
                                                                                  .value()));

    }
}
