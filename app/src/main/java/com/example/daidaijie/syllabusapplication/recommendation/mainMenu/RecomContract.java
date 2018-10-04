package com.example.daidaijie.syllabusapplication.recommendation.mainMenu;

import com.example.daidaijie.syllabusapplication.base.BasePresenter;
import com.example.daidaijie.syllabusapplication.base.BaseView;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;

import java.util.List;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public interface RecomContract {
    interface presenter  extends BasePresenter {

    }
    interface view extends BaseView<RecomContract.presenter> {
        void showList(List<UnitBean> unitBeen);
    }
}
