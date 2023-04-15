package com.multipart.client.multipartclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class WebClientConfig {
    @Bean
    TestApi testApi() {
        var webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        return HttpServiceProxyFactory.builder().customArgumentResolver(new CustomMultipartResolver(ReactiveAdapterRegistry.getSharedInstance()))
                .clientAdapter(WebClientAdapter.forClient(webClient)).build().createClient(TestApi.class);
    }

}
