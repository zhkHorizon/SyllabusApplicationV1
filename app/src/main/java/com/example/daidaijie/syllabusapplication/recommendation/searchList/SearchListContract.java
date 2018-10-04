package com.example.daidaijie.syllabusapplication.recommendation.searchList;

import com.example.daidaijie.syllabusapplication.base.BasePresenter;
import com.example.daidaijie.syllabusapplication.base.BaseView;
import com.example.daidaijie.syllabusapplication.recommendation.SearchClassResultBean;
import com.example.daidaijie.syllabusapplication.recommendation.mainMenu.RecomContract;

import java.util.List;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public interface SearchListContract {
    interface presenter  extends BasePresenter {
        void loadDataForUnit(int UnitID);
        void loadDataForSearch(String text) ;
    }
    interface view extends BaseView<RecomContract.presenter> {
        void showList(List<SearchClassResultBean> unitBeen);
    }
}
