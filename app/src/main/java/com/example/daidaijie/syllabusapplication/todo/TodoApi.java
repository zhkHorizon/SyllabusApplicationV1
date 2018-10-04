package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanFromNet;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 16zhchen on 2018/9/28.
 */

public interface TodoApi {
    @GET("Task")
    Observable<HttpResult<List<TaskBeanFromNet>>> getAllTask(@Query("uid") int uid);

    @POST("Task1")
    Observable<HttpResult<String>> updateTask(@Body PostTaskBean taskBean);

    @POST("Task1")
    Observable<HttpResult<String>> deleteTask(@Body long taskID);

    @POST("Task1")
    Observable<HttpResult<String>> addTask(@Body PostTaskBean taskBean);
}
