package com.addcel.handler;

import com.addcel.client.TransactionGatewayClient;
import com.addcel.request.SitelIngresaRecargaRequest;
import com.addcel.response.SitelIngresaRecargaResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Map;

@Slf4j
@Introspected
public class SitelIngresaRecargaHandler extends MicronautRequestHandler<SitelIngresaRecargaRequest, SitelIngresaRecargaResponse> {

    @Inject
    private TransactionGatewayClient transactionGatewayClient;
    private static final String SITEL_INGRESA_RECARGA_OPERATION = "sitel_ingresa_recarga";


    @Override
    public SitelIngresaRecargaResponse execute(SitelIngresaRecargaRequest input) {
        log.info("execute function...");
        int httpStatus = this.transactionGatewayClient.doPost(SITEL_INGRESA_RECARGA_OPERATION);
        SitelIngresaRecargaResponse sitelIngresaRecargaResponse = new SitelIngresaRecargaResponse();
        sitelIngresaRecargaResponse.setHttpStatus(httpStatus);
        return sitelIngresaRecargaResponse;
    }
}
