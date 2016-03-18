package com.lpf.refreshpage.engine;

import com.lpf.refreshpage.model.BannerModel;
import com.lpf.refreshpage.model.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by liupf5 on 2016/3/15.
 */
public interface Engine {

    @GET("refreshlayout/api/defaultdata6.json")
    Call<List<RefreshModel>> loadInitDatas();

    @GET("refreshlayout/api/newdata{pageNumber}.json")
    Call<List<RefreshModel>> loadNewData(@Path("pageNumber") int pageNumber);

    @GET("refreshlayout/api/moredata{pageNumber}.json")
    Call<List<RefreshModel>> loadMoreData(@Path("pageNumber") int pageNumber);

//    @GET("refreshlayout/api/staggered_default.json")
//    Call<List<StaggeredModel>> loadDefaultStaggeredData();
//
//    @GET("refreshlayout/api/staggered_new{pageNumber}.json")
//    Call<List<StaggeredModel>> loadNewStaggeredData(@Path("pageNumber") int pageNumber);
//
//    @GET("refreshlayout/api/staggered_more{pageNumber}.json")
//    Call<List<StaggeredModel>> loadMoreStaggeredData(@Path("pageNumber") int pageNumber);

    @GET("banner/api/5item.json")
    Call<BannerModel> getBannerModel();

}
