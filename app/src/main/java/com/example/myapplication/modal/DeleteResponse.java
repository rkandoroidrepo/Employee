package com.example.myapplication.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResponse {
    @SerializedName("success")
    @Expose
    private Success success;

    public Success getSuccess() {
        return success;
    }

}
