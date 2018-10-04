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
    List<UnitBean> list1;

    @Inject
    @PerActivity
    public RecomPresenter(IRecomModel iRecomModel,RecomContract.view view){
        mRecomModel = iRecomModel;
        mView = view;
    }

    @Override
    public void start() {
        list1 = new ArrayList<UnitBean>();
        mRecomModel.getAllUnitFromNet()
                .subscribe(new Subscriber<List<UnitBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: loadingUnitList");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<UnitBean> unitBeen) {
                        List<UnitBean> list = new ArrayList<UnitBean>();
                        for(UnitBean temp:unitBeen){
                            UnitBean t1 = new UnitBean();
                            Log.d(TAG, "onNext: "+temp.getUnitID()+temp.getUnitName());
                            t1.setUnitID(temp.getUnitID());
                            t1.setUnitName(temp.getUnitName());
                            list.add(t1);
                        }
                        mView.showList(list);
                    }
                });

        mRecomModel.getAllUnitFromNetTest()
                .subscribe(new Subscriber<UnitBean>() {
                    @Override
                    public void onCompleted() {
                        mView.showList(list1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UnitBean unitBean) {
                        list1.add(unitBean);
                    }
                });
    }
}
