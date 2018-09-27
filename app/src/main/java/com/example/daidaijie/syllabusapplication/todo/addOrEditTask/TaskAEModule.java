package com.example.daidaijie.syllabusapplication.todo.addOrEditTask;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 16zhchen on 2018/9/16.
 */
@Module
public class TaskAEModule {
    private final TaskAEContract.View mView;
    public TaskAEModule(TaskAEContract.View view){
        mView = view;
    }

    @PerFragment
    @Provides
    TaskAEContract.View provideView(){
        return mView;
    }
}
