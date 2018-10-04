package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import com.example.daidaijie.syllabusapplication.di.qualifier.retrofitQualifier.TestRetrofit;
import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.recommendation.IRecomModel;
import com.example.daidaijie.syllabusapplication.recommendation.RecomModel;
import com.example.daidaijie.syllabusapplication.recommendation.recomApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by 16zhchen on 2018/10/4.
 */
@Module
public class ClassDetailModule {
    private ClassDetailContract.view mView;
    public ClassDetailModule(ClassDetailContract.view view){
        mView = view;
    }

    @PerActivity
    @Provides
    ClassDetailContract.view provideView(){
        return mView;
    }

    @PerActivity
    @Provides
    IRecomModel provideRecomModel(@TestRetrofit Retrofit retrofit){
        return new RecomModel(retrofit.create(recomApi.class));
    }

}
