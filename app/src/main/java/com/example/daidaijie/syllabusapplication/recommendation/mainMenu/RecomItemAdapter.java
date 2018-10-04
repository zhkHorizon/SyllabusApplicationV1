package com.example.daidaijie.syllabusapplication.recommendation.mainMenu;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.recommendation.UnitBean;

import java.util.List;

import butterknife.BindView;


public class RecomItemAdapter extends RecyclerView.Adapter<RecomItemAdapter.ViewHolder> implements View.OnClickListener {
       private List<UnitBean> mData;
       private OnItemClickListener mOnItemClickListener;
       private static final String TAG = "RecomItemAdapter";

       public RecomItemAdapter(List<UnitBean> unitBeen) {
              Log.d(TAG, "RecomItemAdapter: ");
              mData = unitBeen;
       }

       public void updateData(List<UnitBean> data){

              mData.clear();
              mData = data;
              Log.d(TAG, "updateData: "+data.size());
              notifyDataSetChanged();
       }

       @Override
       public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
              Log.d(TAG, "onCreateViewHolder: ");
              View view = LayoutInflater.from(parent.getContext())
                      .inflate(R.layout.item_recom_recycleview,parent,false);
              ViewHolder viewHolder = new ViewHolder(view);
              view.setOnClickListener(this);
              return viewHolder;
       }

       @Override
       public void onBindViewHolder(ViewHolder holder, int position) {
              Log.d(TAG, "onBindViewHolder: ");
              holder.mText.setText(mData.get(position).getUnitName());
              holder.itemView.setTag(position);
       }

       public int getClassId(int position){
              Log.d(TAG, "getClassId: ");
              return mData.get(position).getUnitID();
       }

       @Override
       public int getItemCount() {
              Log.d(TAG, "getItemCount: ");
              return mData == null ? 0 : mData.size();
       }

       @Override
       public void onClick(View view) {
              Log.d(TAG, "onClick: ");
              if(mOnItemClickListener != null){
                     mOnItemClickListener.onItemClick(view,(int)view.getTag());
              }
       }

       public static class ViewHolder extends RecyclerView.ViewHolder{
              //@BindView(R.id.item_recom_text)
              TextView mText;

              public ViewHolder(View itemView) {
                     super(itemView);
                     Log.d(TAG, "ViewHolder: ");
                     mText = (TextView) itemView.findViewById(R.id.item_recom_text);
              }
       }
       public interface OnItemClickListener{
              void onItemClick(View view,int position);
       }
       public void setOnItemClickListener(OnItemClickListener listener){
              this.mOnItemClickListener = listener;
       }
}
