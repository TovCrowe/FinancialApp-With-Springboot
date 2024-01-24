package com.example.FinancialTransactions.DTO;

import com.example.FinancialTransactions.Models.UserModel;

public class ApiResponseDTO {

    private boolean success;
    private Object data;
    private String response;

    public ApiResponseDTO(boolean success, Object data, String response) {
        this.success = success;
        this.data = data;
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
