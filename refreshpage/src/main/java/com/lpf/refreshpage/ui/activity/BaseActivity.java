package com.lpf.refreshpage.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lpf.refreshpage.App;
import com.lpf.refreshpage.R;
import com.lpf.refreshpage.engine.Engine;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by liupf5 on 2016/3/15.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected String TAG;
    protected App mApp;
    protected Engine mEngine;
    private SweetAlertDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mApp = App.getInstance();
        mEngine = mApp.getEngine();

        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);
    }

    /**
     * 查找View
     * @param id   控件id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id){ return (VT)findViewById(id);}

    /**
     * 初始化布局以及View控件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 需要处理的点击事件，重写该方法
     * @param v
     */
    @Override
    public void onClick(View v) {
    }

    /**
     * 数据加载对话框
     */
    public void showLoadingDialog(){
        if(mLoadingDialog == null){
            mLoadingDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimaryDark));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText("数据加载...");
        }
        mLoadingDialog.show();
    }

    /**
     * 对话框结束
     */
    public void dismissLoadingDialog(){
        if(mLoadingDialog !=null){
            mLoadingDialog.dismiss();
        }
    }
}
