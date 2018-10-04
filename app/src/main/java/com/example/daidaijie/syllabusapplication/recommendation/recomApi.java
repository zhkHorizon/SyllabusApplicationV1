package com.example.daidaijie.syllabusapplication.recommendation;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface recomApi {

    @GET("RecomUnitAll1")
    Observable<HttpResult<List<UnitBean>>> getAllUnit();

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("RecomUnitAll3")
    Observable<HttpResult<List<ClassDetailBean>>> getClassDetailByClassName(@Field("className") String className);

    @GET("RecomUnitAll2")
    Observable<HttpResult<List<SearchClassResultBean>>> getResultFromUnit(@Query("TYPE")int type,
                                                                            @Query("UnitID") int unitID);
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("RecomUnitAll2")
    Observable<HttpResult<List<SearchClassResultBean>>> getResultFromSearch(@Field("TYPE")int type,@Field("keyword") String text);


}
