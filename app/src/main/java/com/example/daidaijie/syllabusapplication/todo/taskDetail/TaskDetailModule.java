package com.example.daidaijie.syllabusapplication.todo.taskDetail;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;
import com.example.daidaijie.syllabusapplication.todo.mainMenu.TaskListContract;

import dagger.Module;
import dagger.Provides;

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
}
