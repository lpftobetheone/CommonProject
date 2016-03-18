package com.lpf.refreshpage.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpf.refreshpage.App;
import com.lpf.refreshpage.engine.Engine;
import com.lpf.refreshpage.ui.activity.BaseActivity;

/**
 * Created by liupf5 on 2016/3/15.
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG;
    protected App mApp;
    protected View mContentView;
    protected Engine mEngine;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        mApp = App.getInstance();
        mActivity = (BaseActivity) activity;
        mEngine = mApp.getEngine();
    }

    /**
     * Fragment懒加载，在UI显示的时候才去加载数据
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onUserVisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //避免多次从xml中加载布局文件
        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected void setContentView(@LayoutRes int layoutResID){
        mContentView = LayoutInflater.from(mApp).inflate(layoutResID,null);
    }

    /**
     * 初始化View控件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复操作
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 当fragment对用户可见时，调用此方法，可在该方法中懒加载网络数据
     */
    protected abstract void onUserVisible();

    /**
     * 查找View
     * @param id
     * @param <VT>
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id){
        return (VT)mContentView.findViewById(id);
    }

    /**
     * 调用Activity中对应的显示和消失对话框方法
     */
    protected void showLoadingDialog(){
        mActivity.showLoadingDialog();
    }

    protected void dismissLoadingDialog(){
        mActivity.dismissLoadingDialog();
    }
}
