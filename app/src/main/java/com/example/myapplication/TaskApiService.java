package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface TaskApiService {
    @POST("/taches/add")
    Call<TaskResponse> addTask(@Body TaskRequest task);

    @GET("/taches/get/{id}")
    Call<TaskResponse> getTask(@Path("id") String taskId);

    @PATCH("/taches/update/{id}")
    Call<TaskResponse> updateTask(@Path("id") String taskId, @Body TaskRequest task);

    @DELETE("/taches/delete/{id}")
    Call<Void> deleteTask(@Path("id") String taskId);

    @GET("/taches/list")
    Call<List<TaskResponse>> getTaskList();
}

