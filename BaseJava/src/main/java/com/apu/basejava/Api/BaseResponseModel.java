package com.apu.basejava.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BaseResponseModel<T> {
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess = false;
    @SerializedName("Data")
    @Expose
    private T data;
}
