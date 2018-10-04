package com.example.daidaijie.syllabusapplication.recommendation.searchList;

import android.util.Log;

import com.example.daidaijie.syllabusapplication.di.scope.PerActivity;
import com.example.daidaijie.syllabusapplication.recommendation.IRecomModel;
import com.example.daidaijie.syllabusapplication.recommendation.SearchClassResultBean;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class SearchListPrsenter implements SearchListContract.presenter{
    private IRecomModel mRecomModel;
    private SearchListContract.view mView;
    private static final String TAG = "SearchListPrsenter";

    @Inject
    @PerActivity
    public SearchListPrsenter(IRecomModel iRecomModel,SearchListContract.view view){
        mRecomModel = iRecomModel;
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadDataForUnit(int UnitID) {
        Log.d(TAG, "loadDataForUnit: UNITID"+UnitID);
        mRecomModel.getResultFromUnit(UnitID)
                .subscribe(new Subscriber<List<SearchClassResultBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: loadingUnitList");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<SearchClassResultBean> unitBeen) {
                        List<SearchClassResultBean> list = new ArrayList<SearchClassResultBean>();
                        for(SearchClassResultBean temp:unitBeen){
                            SearchClassResultBean t1 = new SearchClassResultBean();
                            t1.setClassName(temp.getClassName());
                            list.add(t1);
                        }
                        mView.showList(list);
                    }
                });
    }

    @Override
    public void loadDataForSearch(String text) {

        mRecomModel.getResultFromSearch(text)

                .subscribe(new Subscriber<List<SearchClassResultBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: loadingUnitList");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<SearchClassResultBean> unitBeen) {
                        List<SearchClassResultBean> list = new ArrayList<SearchClassResultBean>();
                        for(SearchClassResultBean temp:unitBeen){
                            SearchClassResultBean t1 = new SearchClassResultBean();
                            t1.setClassName(temp.getClassName());
                            list.add(t1);
                        }
                        mView.showList(list);
                    }
                });


    }
}
