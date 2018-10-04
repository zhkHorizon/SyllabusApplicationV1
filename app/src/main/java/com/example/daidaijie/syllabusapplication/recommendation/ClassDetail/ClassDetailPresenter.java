package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.recommendation.ClassDetailBean;
import com.example.daidaijie.syllabusapplication.recommendation.IRecomModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class ClassDetailPresenter implements ClassDetailContract.presenter {
    private IRecomModel mRecomModel;
    private ClassDetailContract.view mView;
    private List<ClassDetailBean> list;
    private static final String TAG = "ClassDetailPresenter";

    @Inject
    @PerActivity
    public ClassDetailPresenter(IRecomModel iRecomModel,ClassDetailContract.view view){
        mRecomModel = iRecomModel;
        mView = view;
    }
    @Override
    public void loadData(String className) {
        Log.d(TAG, "loadData:CLASSNAME "+className);
        mRecomModel.getClassDetailByCLassName(className)
                .subscribe(new Subscriber<ClassDetailBean>() {
                    @Override
                    public void onCompleted() {
                        Comparator<ClassDetailBean> com = new ClassScoreComparatpor();
                        Collections.sort(list,com);
                        mView.showList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassDetailBean classDetailBean) {
                        list.add(classDetailBean);
                    }
                });
    }

    @Override
    public void start() {
        list = new ArrayList<>();
    }
}
