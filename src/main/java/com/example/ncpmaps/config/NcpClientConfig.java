package com.example.ncpmaps.config;

import com.example.ncpmaps.client.NcpHttpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
public class NcpClientConfig {
    @Value("${ncp.api.client-id}") String clientId;
    @Value("${ncp.api.client-secret}") String clientSecret;


    @Bean
    public RestClient ncpMapClient() {
        return RestClient.builder()
                .baseUrl("https://naveropenapi.apigw.ntruss.com")
//                .requestInitializer(request -> request.getHeaders().setContentType(MediaType.APPLICATION_JSON))
                .defaultHeader("X-NCP-APIGW-API-KEY-ID", clientId)
                .defaultHeader("X-NCP-APIGW-API-KEY", clientSecret)
                .build();
    }

    @Bean
    public NcpHttpInterface ncpHttpInterface() {
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(ncpMapClient()))
                .build().createClient(NcpHttpInterface.class);
    }

}
