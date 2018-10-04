package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;

import com.example.daidaijie.syllabusapplication.base.BasePresenter;
import com.example.daidaijie.syllabusapplication.base.BaseView;
import com.example.daidaijie.syllabusapplication.recommendation.ClassDetailBean;

import java.util.List;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public interface ClassDetailContract {
    interface presenter  extends BasePresenter {
        void loadData(String className);
    }
    interface view extends BaseView<ClassDetailContract.presenter> {
        void showList(List<ClassDetailBean> list);
    }
}
