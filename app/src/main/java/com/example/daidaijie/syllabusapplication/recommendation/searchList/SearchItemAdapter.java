package com.example.daidaijie.syllabusapplication.recommendation.searchList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.recommendation.SearchClassResultBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 16zhchen on 2018/10/4.
 */

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> implements View.OnClickListener {
    private List<SearchClassResultBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public SearchItemAdapter(List<SearchClassResultBean> list){
        mData = list;
    }

    public void updateData(List<SearchClassResultBean> list){
        mData.clear();
        mData = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recom_recycleview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(mData.get(position).getClassName());
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public String getClassName(int position){
        return mData.get(position).getClassName();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.item_recom_text);
            mText.setCompoundDrawables(null,null,null,null);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
