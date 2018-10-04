package com.example.daidaijie.syllabusapplication.todo.taskDetail;

import com.example.daidaijie.syllabusapplication.di.qualifier.retrofitQualifier.TestRetrofit;
import com.example.daidaijie.syllabusapplication.di.qualifier.user.LoginUser;
import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.TodoApi;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.ITaskModel;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.TaskListContract;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.TaskModel;
import com.example.daidaijie.syllabusapplication.user.IUserModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by 16zhchen on 2018/9/16.
 */
@Module
public class TaskDetailModule {
    private final TaskDetailContract.View mView;
    public TaskDetailModule(TaskDetailContract.View view){
        mView = view;
    }

    @PerFragment
    @Provides
    TaskDetailContract.View provideView(){
        return mView;
    }


    @PerFragment
    @Provides
    ITaskModel provideTaskModel(@TestRetrofit Retrofit retrofit,
                                @LoginUser IUserModel iUserModel){
        return new TaskModel(retrofit.create(TodoApi.class),iUserModel);
    }
}
