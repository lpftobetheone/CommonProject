package com.lpf.refreshpage.util;

import android.os.Handler;

/**
 * Created by liupf5 on 2016/3/15.
 */
public class ThreadUtil {

    private static Handler mHandler = new Handler();

    private ThreadUtil() {

    }

    /**
     * 在子线程中执行任务
     *
     * @param task
     */
    public static void runInThread(Runnable task) {
        new Thread(task).start();
    }

    /**
     * 在UI线程中执行任务
     * @param task
     */
    public static void runInUIThread(Runnable task) {
        mHandler.post(task);
    }

    /**
     * 在UI线程中执行延时任务
     * @param task
     * @param dayMillis
     */
    public static void runInUIThread(Runnable task, long dayMillis){
        mHandler.postDelayed(task,dayMillis);
    }
}
