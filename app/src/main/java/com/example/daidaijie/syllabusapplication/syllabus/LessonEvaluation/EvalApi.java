package com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation;

import com.example.daidaijie.syllabusapplication.bean.HttpResult;
import com.example.daidaijie.syllabusapplication.bean.LessonDetailInfo;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 16zhchen on 2018/9/19.
 */

public interface EvalApi {
    //TODO，修改为后台给的地址

    @GET("s")
    Observable<HttpResult<EvalBean>> getLessonEval(@Query("class_id") long lessonID,
                                                           @Query("uid") int uid);

    @POST("r")
    Observable<HttpResult<String>> sendComment(@Body PostEvalBean postEvalBean);
}
