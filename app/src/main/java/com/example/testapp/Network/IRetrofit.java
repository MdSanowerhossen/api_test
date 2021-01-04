package com.example.testapp.Network;

import com.example.testapp.NetworkModel.TestResponse;

import java.util.List;

import retrofit2.http.GET;

public interface IRetrofit {
    @GET("api/get_data.php?fbclid=IwAR0fKepqr-WuUzA3UKK5whnrXqJoD96YcmZ4X2JuG6Ve4SXVq6TbzzKTdFM")
    io.reactivex.Observable<List<TestResponse.Data>> getData();
}
