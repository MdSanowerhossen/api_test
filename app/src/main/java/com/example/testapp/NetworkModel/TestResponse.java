package com.example.testapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestResponse {

    public Data data;

    public class Data{
        @SerializedName("name")
        public String name;

        @SerializedName("capital")
        public String capital;

        @SerializedName("currency")
        public String currency;
    }
}
