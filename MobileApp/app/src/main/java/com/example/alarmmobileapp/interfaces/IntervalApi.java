package com.example.alarmmobileapp.interfaces;
import com.example.alarmmobileapp.classes.IntervalRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IntervalApi {
    @POST("/delete_interval")
    Call<Void> deleteInterval(@Body IntervalRequest intervalRequest);
    @POST("/add_interval")
    Call<Void> addInterval(@Body IntervalRequest intervalRequest);
}
