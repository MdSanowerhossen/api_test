package com.example.testapp.Utils;

import com.example.testapp.Network.IRetrofit;
import com.example.testapp.Network.RetrofitClient;

public abstract class Common {
    public static final String BASE_URL_AROBIL = "https://myprojectbd24.com/";

    public static IRetrofit getApi() {
        return RetrofitClient.getClient(BASE_URL_AROBIL).create(IRetrofit.class);
    }

}
