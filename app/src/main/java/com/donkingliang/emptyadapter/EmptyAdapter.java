package com.donkingliang.emptyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author donkingliang
 * @Description
 * @Date 2020/4/29
 */
public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.ViewHolder> {


    private static final int TYPE_ITEM = 1;
    private static final int TYPE_EMPTY = 2;

    private Context mContext;

    // 数据
    protected List<String> mList;

    // 是否显示空布局
    private boolean showEmptyView = false;

    public EmptyAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY) {
            return new ViewHolder(getEmptyView(parent));
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, parent, false);
            return new ViewHolder(view);
        }
    }

    /**
     * 获取空布局
     *
     * @param parent
     * @return
     */
    private View getEmptyView(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_empty_view, parent, false);
        Button btnLoadData = view.findViewById(R.id.btn_load_data);
        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setList(initData());
            }
        });
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!isEmptyPosition(position)) {
            holder.tvItem.setText(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int count = mList != null ? mList.size() : 0;
        if (count > 0) {
            return count;
        } else if (showEmptyView) {
            // 显示空布局
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmptyPosition(position)) {
            // 空布局
            return TYPE_EMPTY;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setList(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    /**
     * 判断是否是空布局
     */
    public boolean isEmptyPosition(int position) {
        int count = mList != null ? mList.size() : 0;
        return position == 0 && showEmptyView && count == 0;
    }

    /**
     * 设置空布局显示。默认不显示
     *
     * @param isShow
     */
    public void showEmptyView(boolean isShow) {
        if (isShow != showEmptyView) {
            showEmptyView = isShow;
            notifyDataSetChanged();
        }
    }

    public boolean isShowEmptyView() {
        return showEmptyView;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    /**
     * 生成列表数据
     * @return
     */
    private List<String> initData() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add("Item " + (i + 1));
        }
        return list;
    }

}
