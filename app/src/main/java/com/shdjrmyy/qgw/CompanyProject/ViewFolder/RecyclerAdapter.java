package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;


import com.shdjrmyy.qgw.CompanyProject.R;

import java.util.List;

/**
 * Created by Simple on 2016/9/7.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecycleHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private LayoutInflater mInflater;

    private OnItemClickListener onItemClickListener;
    private int lastAnimatedPosition;



    public RecyclerAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
        if(mDatas.size()==0)this.mLayoutId = R.layout.zwsj;
    }
    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里是创建ViewHolder的地方，RecyclerAdapter内部已经实现了ViewHolder的重用
        //这里我们直接new就好了
        return new RecycleHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    public void setOnItemClickListener(OnItemClickListener i){
        this.onItemClickListener = i;
    }

    @Override
    public void onBindViewHolder(final RecycleHolder holder, int position) {
        //runEnterAnimation(holder.itemView,position);

        if (onItemClickListener != null) {
            //设置背景
            holder.itemView.setBackgroundResource(R.drawable.recycler_bg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //注意，这里的position不要用上面参数中的position，会出现位置错乱
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if(mDatas.size()!=0) convert(holder, mDatas.get(position), position);


        }

    public  abstract void convert(RecycleHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        if(mDatas.size()==0)return 1;
        return mDatas.size();
    }



    /**自定义RecyclerView item的点击事件的点击事件*/
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

    private void runEnterAnimation(View view, int position) {

        if (true) return;//animationsLocked是布尔类型变量，一开始为false，确保仅屏幕一开始能够显示的item项才开启动画

        if (position > lastAnimatedPosition) {//lastAnimatedPosition是int类型变量，一开始为-1，这两行代码确保了recycleview滚动式回收利用视图时不会出现不连续的效果
            lastAnimatedPosition = position;
            view.setTranslationY(500);//相对于原始位置下方400
            view.setAlpha(0.f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(true ? 20 * (position) : 0)//根据item的位置设置延迟时间，达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))//设置动画效果为在动画开始的地方快然后慢
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            boolean animationsLocked = true;//确保仅屏幕一开始能够显示的item项才开启动画，也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })
                    .start();
        }
    }
}
