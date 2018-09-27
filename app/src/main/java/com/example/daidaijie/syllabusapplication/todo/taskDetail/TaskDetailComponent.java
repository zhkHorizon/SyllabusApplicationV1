package com.example.daidaijie.syllabusapplication.todo.taskDetail;

import com.example.daidaijie.syllabusapplication.di.scope.PerFragment;

import dagger.Component;

/**
 * Created by 16zhchen on 2018/9/16.
 */
@PerFragment
@Component(dependencies = TaskDetailModule.class)
public interface TaskDetailComponent {
    void inject(TaskDetailFragment taskDetailFragment);
}
