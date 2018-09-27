package com.example.daidaijie.syllabusapplication.todo.addOrEditTask;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;

import dagger.Component;

/**
 * Created by 16zhchen on 2018/9/16.
 */
@PerFragment
@Component(dependencies = TaskAEModule.class)
public interface TaskAEComponent {
    void inject(TaskAEFragment taskAEFragment);
}
