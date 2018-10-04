package com.example.daidaijie.syllabusapplication.recommendation.ClassDetail;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.recommendation.ClassDetailBean;

import java.util.List;

public class ClassItemAdapter extends RecyclerView.Adapter<ClassItemAdapter.ViewHolder> implements View.OnClickListener{

    private List<ClassDetailBean> mData;
    private OnItemClickListener mOnItemClickListener;
    private static final String TAG = "ClassItemAdapter";

    public ClassItemAdapter(List<ClassDetailBean> list) {
        Log.d(TAG, "ClassItemAdapter: ");
        mData = list;
    }

    public void updateData(List<ClassDetailBean> list){

        mData.clear();
        mData = list;
        Log.d(TAG, "updateData: "+list.size());
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class_detail_recycleview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.className.setText(mData.get(position).getClassName());
        holder.teacher.setText(mData.get(position).getTeacherName());
        holder.score.setText(String.valueOf(mData.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return  mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView className;
        TextView teacher;
        TextView score;
        public ViewHolder(View itemView) {

            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            className = (TextView) itemView.findViewById(R.id.item_class_detail_name);
            teacher = (TextView) itemView.findViewById(R.id.item_class_detail_teacher);
            score = (TextView) itemView.findViewById(R.id.item_class_detail_score);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
