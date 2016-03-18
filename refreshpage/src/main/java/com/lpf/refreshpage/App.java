package com.lpf.refreshpage;

import android.app.Application;
import com.lpf.refreshpage.engine.Engine;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liupf5 on 2016/3/15.
 */
public class App extends Application{

    private static App mInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }

    public static App getInstance(){
        return mInstance;
    }

    public Engine getEngine(){
        return mEngine;
    }
}
