package com.example.daidaijie.syllabusapplication.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.todo.dataTemp.TaskBean;

import java.util.List;


/**
 * 任务列表中RecycleView的Adapter
 */

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> implements View.OnClickListener{
    private List<TaskBean> mData;
    private OnItemClickListener mOnItemClickListener;
    private static final String TAG = "TaskListFragment";

    public TaskItemAdapter(List<TaskBean> data){
        this.mData = data;
    }

    public void updateData(List<TaskBean> data){
        this.mData = data;
        Log.d("adapter_len", String.valueOf(data.size()));
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasklist_recycle,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskBean task = mData.get(position);
        Log.d(TAG, "onBindViewHolder: "+task.getId()+" "+task.getTitle()+" "+task.getIsAlarm());
        String str ="";
        //TODO
        if(task.getStatus()==0)
            str = "进行中";
        else if(task.getStatus()==1)
            str = "已完成";
//        if(task.getStatus()==0)
//            str = "";
//        else if(task.getStatus()==1)
//            str = "";
//        if(task.getStatus()==0)
//            holder.noStatus.setChecked(false);
//        else if(task.getStatus()==1){
//            holder.noStatus.setChecked(true);
//            holder.noTitle.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String date = sdf.format(task.getTIME())+ "\n" + sdf.format(task.getFinishTime());

        holder.noTitle.setText(task.getTitle());
        holder.noState.setText(str);
        //holder.noTime.setText(date);
        holder.itemView.setTag(position);
        if(task.getIsAlarm()){
            holder.noAlarm.setVisibility(View.VISIBLE);
        }else{
            holder.noAlarm.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView noTitle;
        TextView noState;
        ImageView noAlarm;
        CheckBox noStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            noTitle = (TextView) itemView.findViewById(R.id.item_noTitle);
            noState = (TextView) itemView.findViewById(R.id.item_noState);
            noAlarm = (ImageView) itemView.findViewById(R.id.item_alarmImage);
            //noStatus = (CheckBox) itemView.findViewById(R.id.item_statis) ;
            noAlarm.setVisibility(View.INVISIBLE);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public TaskBean getTask(int position){
        return mData.get(position);
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
