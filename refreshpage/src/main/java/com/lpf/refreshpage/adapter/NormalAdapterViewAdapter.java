package com.lpf.refreshpage.adapter;

import android.content.Context;

import com.lpf.refreshpage.R;
import com.lpf.refreshpage.model.RefreshModel;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by liupf5 on 2016/3/16.
 */
public class NormalAdapterViewAdapter extends BGAAdapterViewAdapter<RefreshModel> {
    public NormalAdapterViewAdapter(Context context) {
        super(context, R.layout.item_normal);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    protected void fillData(BGAViewHolderHelper bgaViewHolderHelper, int i, RefreshModel refreshModel) {
        bgaViewHolderHelper.setText(R.id.tv_item_normal_title,refreshModel.title)
                .setText(R.id.tv_item_normal_detail,refreshModel.detail);
    }
}
