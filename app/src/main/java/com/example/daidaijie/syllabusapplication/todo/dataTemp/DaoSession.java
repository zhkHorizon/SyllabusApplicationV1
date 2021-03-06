package com.example.daidaijie.syllabusapplication.todo.dataTemp;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.daidaijie.syllabusapplication.syllabus.LessonEvaluation.local.EvalBean;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import com.example.daidaijie.syllabusapplication.todo.dataTemp.EvalBeanDao;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig evalBeanDaoConfig;
    private final DaoConfig taskBeanDaoConfig;

    private final EvalBeanDao evalBeanDao;
    private final TaskBeanDao taskBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        evalBeanDaoConfig = daoConfigMap.get(EvalBeanDao.class).clone();
        evalBeanDaoConfig.initIdentityScope(type);

        taskBeanDaoConfig = daoConfigMap.get(TaskBeanDao.class).clone();
        taskBeanDaoConfig.initIdentityScope(type);

        evalBeanDao = new EvalBeanDao(evalBeanDaoConfig, this);
        taskBeanDao = new TaskBeanDao(taskBeanDaoConfig, this);

        registerDao(EvalBean.class, evalBeanDao);
        registerDao(TaskBean.class, taskBeanDao);
    }
    
    public void clear() {
        evalBeanDaoConfig.getIdentityScope().clear();
        taskBeanDaoConfig.getIdentityScope().clear();
    }

    public EvalBeanDao getEvalBeanDao() {
        return evalBeanDao;
    }

    public TaskBeanDao getTaskBeanDao() {
        return taskBeanDao;
    }

}
