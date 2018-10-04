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
    private List<SearchClassResultBean> list ;
    private static final String TAG = "SearchListPrsenter";

    @Inject
    @PerActivity
    public SearchListPrsenter(IRecomModel iRecomModel,SearchListContract.view view){
        mRecomModel = iRecomModel;
        mView = view;
    }

    @Override
    public void start() {
        list = new ArrayList<>();
    }

    @Override
    public void loadDataForUnit(int UnitID) {
        Log.d(TAG, "loadDataForUnit: UNITID"+UnitID);
        list.clear();
        mRecomModel.getResultFromUnit(UnitID)
                .subscribe(new Subscriber<SearchClassResultBean>() {
                    @Override
                    public void onCompleted() {
                        mView.showList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchClassResultBean searchClassResultBean) {
                        list.add(searchClassResultBean);
                    }
                });
    }

    @Override
    public void loadDataForSearch(String text) {
        list.clear();
        mRecomModel.getResultFromSearch(text)

                .subscribe(new Subscriber<SearchClassResultBean>() {
                    @Override
                    public void onCompleted() {
                        mView.showList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchClassResultBean searchClassResultBean) {
                        list.add(searchClassResultBean);
                    }
                });


    }
}
