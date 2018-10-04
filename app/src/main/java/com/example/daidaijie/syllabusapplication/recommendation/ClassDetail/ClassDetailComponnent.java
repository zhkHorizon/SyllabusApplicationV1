package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import com.example.daidaijie.syllabusapplication.AppComponent;
import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.recommendation.searchList.SearchListModule;

import dagger.Component;

/**
 * Created by 16zhchen on 2018/10/4.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ClassDetailModule.class)
public interface ClassDetailComponnent {
    void inject(ClassDetailAcvitity classDetailAcvitity);
}
