package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.bean.TaskAllBean;
import com.example.daidaijie.syllabusapplication.di.qualifier.realm.UserRealm;
import com.example.daidaijie.syllabusapplication.di.qualifier.retrofitQualifier.TodoRetrofit;
import com.example.daidaijie.syllabusapplication.di.scope.PerModule;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;

@Module
public class TaskDataModule {
    private TaskAllBean mTaskAllBean;

    public TaskDataModule(TaskAllBean taskAllBean){
        mTaskAllBean = taskAllBean;
    }

    @PerModule
    @Provides
    TaskAllBean provideTaskAllBean(){
        return mTaskAllBean;
    }

    @PerModule
    @Provides
    ITaskModel provideTaskModel(@TodoRetrofit Retrofit retrofit,
                                TaskAllBean taskAllBean,
                                @UserRealm Realm realm){
        //return new TaskModel(retrofit.create(TaskApi.class),taskAllBean,realm);
        return null;
    }
}
