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
                .subscribe(new Subscriber<List<ClassDetailBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: loadingUnitList");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<ClassDetailBean> unitBeen) {
                        List<ClassDetailBean> list = new ArrayList<ClassDetailBean>();
                        for(ClassDetailBean temp:unitBeen){
                            ClassDetailBean t1 = new ClassDetailBean();
                            t1.setClassName(temp.getClassName());
                            t1.setClassID(temp.getClassID());
                            t1.setScore(temp.getScore());
                            t1.setTeacherName(temp.getTeacherName());
                            list.add(t1);
                        }
                        Comparator<ClassDetailBean> com = new ClassScoreComparatpor();
                        Collections.sort(list,com);
                        mView.showList(list);
                    }
                });
    }

    @Override
    public void start() {

    }
}
