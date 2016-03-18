package com.lpf.refreshpage.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.lpf.refreshpage.R;
import com.lpf.refreshpage.adapter.NormalAdapterViewAdapter;
import com.lpf.refreshpage.model.RefreshModel;
import com.lpf.refreshpagelib.BGAMoocStyleRefreshViewHolder;
import com.lpf.refreshpagelib.BGARefreshLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class RefreshGridViewFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener {

    @InjectView(R.id.beginRefreshing)
    TextView beginRefreshing;
    @InjectView(R.id.beginLoadingMore)
    TextView beginLoadingMore;
    private BGARefreshLayout mRefreshLayout;
    private GridView mDataGv;
    private NormalAdapterViewAdapter mAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_refresh_grid_view);
        mRefreshLayout = getViewById(R.id.rl_gridview_refresh);
        mDataGv = getViewById(R.id.lv_gridview_data);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);


        mAdapter = new NormalAdapterViewAdapter(mApp);
//        mAdapter.setOnItemChildClickListener(this);
//        mAdapter.setOnItemChildLongClickListener(this);

        beginLoadingMore.setOnClickListener(this);
        beginRefreshing.setOnClickListener(this);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.drawable.bga_refresh_moooc);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);

        mDataGv.setAdapter(mAdapter);
    }

    @Override
    protected void onUserVisible() {
        mEngine.loadInitDatas().enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mAdapter.setDatas(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {

            }
        });
    }

    public RefreshGridViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refresh_grid_view, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
