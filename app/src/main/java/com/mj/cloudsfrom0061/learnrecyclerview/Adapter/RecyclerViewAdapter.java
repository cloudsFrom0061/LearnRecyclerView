package com.mj.cloudsfrom0061.learnrecyclerview.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mj.cloudsfrom0061.learnrecyclerview.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder > {

    private List<String> datas;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context,List<String> datas){
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemViewHolder itemViewHolder = new ItemViewHolder(inflater.inflate(R.layout.item,viewGroup,false));
        return itemViewHolder;
    }

    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.textView.setText(datas.get(i));
        if (onItemClickListener!=null){
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             * */
            if (!itemViewHolder.textView.hasOnClickListeners()){
                itemViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = itemViewHolder.getPosition();
                        onItemClickListener.onItemClick(view,position);
                    }
                });
                itemViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int positon = itemViewHolder.getPosition();
                        onItemClickListener.onLongItemClick(view,positon);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 向指定地点添加元素
     * @param position
     * @param value
     */
    public void add(int position,String value){
        if (position>datas.size())position = datas.size();
        if (position<0) position = 0;
        datas.add(position,value);
        /**
         * 使用notifyItemInserted(position);会有动画效果
         */
        notifyItemInserted(position);
    }

    /**
     * 移除指定地点的元素
     * @param position
     */
    public String remove(int position){
        if (position>datas.size()-1) return null;
        String value = datas.get(position);
        notifyItemRemoved(position);
        return value;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
        public void onLongItemClick(View v,int positon);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
