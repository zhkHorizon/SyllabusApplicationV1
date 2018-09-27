package com.example.daidaijie.syllabusapplication.todo.mainMenu;

import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 16zhchen on 2018/9/16.
 */
@Module
public class TaskListModule {
    private final TaskListContract.view mView;
    public TaskListModule(TaskListContract.view view){
        mView = view;
    }

    @PerFragment
    @Provides
    TaskListContract.view provideView(){
        return mView;
    }
}
