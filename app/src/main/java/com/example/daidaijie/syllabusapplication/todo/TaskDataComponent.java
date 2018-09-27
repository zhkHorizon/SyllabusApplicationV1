package com.example.daidaijie.syllabusapplication.todo;

import com.example.daidaijie.syllabusapplication.bean.TaskAllBean;
import com.example.daidaijie.syllabusapplication.di.scope.PerModule;
import com.example.daidaijie.syllabusapplication.todo.TaskDataModule;
import com.example.daidaijie.syllabusapplication.user.UserComponent;

import dagger.Component;

/**
 * Created by 16zhchen on 2018/9/14.
 */
@PerModule
@Component(dependencies = UserComponent.class, modules = TaskDataModule.class)
public abstract class TaskDataComponent {
    private static TaskDataComponent INSTANCE;
    public static TaskDataComponent newInstance(UserComponent userComponent,
                                                 TaskAllBean taskAllBean){
        if(INSTANCE == null){
            //TODO
            INSTANCE = DaggerTaskDataComponent.builder()
                    .userComponent(userComponent)
                    .taskDataModule(new TaskDataModule(taskAllBean))
                    .build();
        }
        return INSTANCE;
    }
    public static TaskDataComponent getINSTANCE(){
        return INSTANCE;
    }

    public static void destory(){
        INSTANCE = null;
    }
    public abstract ITaskModel getTaskModel();
}
