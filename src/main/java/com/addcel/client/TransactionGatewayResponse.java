package com.addcel.client;

import io.micronaut.core.annotation.Introspected;

import java.util.Map;

@Introspected
public class TransactionGatewayResponse {
    private boolean success;
    private Map<String, Object> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
