package pl.codepot.aggregatr.aggregation.service;

import com.nurkiewicz.asyncretry.RetryExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.codepot.aggregatr.aggregation.model.IngredientsResponse;

import com.netflix.hystrix.HystrixCommand;
import com.nurkiewicz.asyncretry.RetryExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import pl.codepot.aggregatr.aggregation.model.Version;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;
import static com.netflix.hystrix.HystrixCommandGroupKey.Factory;

/**
 * Created by Pawelccb on 2015-08-28.
 */
@Service
public class CommunicationService {

    private final ServiceRestClient serviceRestClient;
    private final RetryExecutor retryExecutor;

    @Autowired
    public CommunicationService(ServiceRestClient serviceRestClient, RetryExecutor retryExecutor) {
        this.serviceRestClient = serviceRestClient;
        this.retryExecutor = retryExecutor;
    }

    public void sendProductionRequest(IngredientsResponse ingredients) {
        serviceRestClient.forService("dojrzewatr")
                .retryUsing(retryExecutor)
                .post()
                .withCircuitBreaker(HystrixCommand.Setter.withGroupKey(asKey("hystrix_group")))
                .onUrl("/brew")
                .body(ingredients)
                .withHeaders().contentType(Version.DOJRZEWATR_V1)
                .andExecuteFor()
                .anObject();
    }
}
