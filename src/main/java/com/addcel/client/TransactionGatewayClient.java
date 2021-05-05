package com.addcel.client;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.micronaut.http.HttpRequest.POST;

@Slf4j
@Singleton
public class TransactionGatewayClient {

    private RxHttpClient httpClient;
    @Value("${com.addcel.transaction-gateway.api-key:}")
    private String apiKey;
    private static final String FORMAT_DATE = "ddMMyyyy";

    @Inject
    public TransactionGatewayClient(@Client("${com.addcel.transaction-gateway.url}") RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public int doPost(String operation) {
        log.info("doPost operation: {}", operation);
        TransactionGatewayRequest request = new TransactionGatewayRequest();
        LocalDate date = LocalDate.now();
        request.setNewPartPassword(date.format(DateTimeFormatter.ofPattern(FORMAT_DATE)));
        try {
            HttpResponse response = this.httpClient.toBlocking().retrieve(
                    POST("/public/transaction-gateway/" + operation, request)
                            .contentType(MediaType.APPLICATION_JSON_TYPE)
                            .header("X-Api-Key", this.apiKey),
                    HttpResponse.class
            );
            log.info("response HTTP STATUS: {}, BODY: {}", response.getStatus(), response.body());
            return response.getStatus().getCode();
        } catch (HttpClientResponseException clientExt) {
            log.error("Http client exception HTTP Status {}", clientExt.getStatus(), clientExt);
            return clientExt.getStatus().getCode();
        }


    }
}