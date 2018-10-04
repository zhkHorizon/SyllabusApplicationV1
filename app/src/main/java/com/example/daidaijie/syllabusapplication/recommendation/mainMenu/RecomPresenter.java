package com.example.daidaijie.syllabusapplication.recommendation.mainMenu;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.recommendation.IRecomModel;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class RecomPresenter implements RecomContract.presenter {
    private IRecomModel mRecomModel;
    private RecomContract.view mView;
    private static final String TAG = "RecomPresenter";
    List<UnitBean> list;

    @Inject
    @PerActivity
    public RecomPresenter(IRecomModel iRecomModel,RecomContract.view view){
        mRecomModel = iRecomModel;
        mView = view;
    }

    @Override
    public void start() {
        list = new ArrayList<>();

        mRecomModel.getAllUnitFromNet()
                .subscribe(new Subscriber<UnitBean>() {
                    @Override
                    public void onCompleted() {
                        mView.showList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(UnitBean unitBean) {
                        list.add(unitBean);
                    }
                });
    }
}
