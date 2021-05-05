package com.addcel.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class SitelIngresaRecargaResponse {
    private Integer httpStatus;

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
