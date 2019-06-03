package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



/**
 * Created by Simple on 2016/9/7.
 */

public class RecycleHolder extends RecyclerView.ViewHolder {

    /** 用于存储当前item当中的View */
    private SparseArray<View> mViews;

    public RecycleHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
    }



    public <T extends View> T findView(int ViewId) {
        View view = mViews.get(ViewId);
        //集合中没有，则从item当中获取，并存入集合当中
        if (view == null) {
            view = itemView.findViewById(ViewId);
            mViews.put(ViewId, view);
        }
        return (T) view;
    }



    public RecycleHolder setSlideClickListener(int menuId, View.OnClickListener listener) {
        View view = findView(menuId);
        view.setOnClickListener(listener);
        return this;
    }

    public RecycleHolder setInputType(int menuId, int inputType) {
        EditText view = (EditText)findView(menuId);
        view.setInputType(inputType);
        return this;
    }

    public RecycleHolder setText(int viewId, String text) {
        if(text==null)text="0.0";
        if(findView(viewId) instanceof CheckBox) {
            CheckBox cb = findView(viewId);
            cb.setText(text);
            return this;
        }
        if(findView(viewId) instanceof EditText) {
            EditText cb = findView(viewId);
            cb.setText(text);
            return this;
        }
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }


    public RecycleHolder setOTCL(int viewId, TextWatcher text) {
        EditText tv = findView(viewId);
        tv.addTextChangedListener(text);
        return this;
    }



    public RecycleHolder setCheckBoxText(int viewId, String text) {
        CheckBox tv = findView(viewId);
        if(text==null)text="";
        tv.setText(text);
        return this;
    }

    public RecycleHolder setText(int viewId, int text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }
    public RecycleHolder setImageResource(int viewId, int ImageId) {
        ImageView image = findView(viewId);
        image.setImageResource(ImageId);
        return this;
    }
    public RecycleHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView image = findView(viewId);
        image.setImageBitmap(bitmap);
        return this;
    }

    ImageView imageView;
    public ImageView getView(){
        return imageView;
    }
    public RecycleHolder setImageNet(int viewId, String url, boolean fillet) {
        ImageView image = findView(viewId);
        imageView = image;
        if (fillet) Glide.with(image.getContext()).load(url).into(image);
        else Glide.with(image.getContext()).load(url).into(image);
        return this;
    }
    public RecycleHolder setImageR(int viewId,int id) {
        ImageView image = findView(viewId);
        imageView = image;
        image.setImageResource(id);
        return this;
    }

    public RecycleHolder setEn(int id,boolean e){
        findView(id).setEnabled(e);
        return this;
    }

    public RecycleHolder setVisibility(int viewId,int t) {
        View view = findView(viewId);
        view.setVisibility(t);
        //修改Gone状态仍然占用位置
//        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
//        if (t == View.GONE){
//            params.height = 0;
//            params.width = 0;
//            itemView.setLayoutParams(params);
//        }
        return this;
    }

    public RecycleHolder setVisibility(int viewId) {
        View view = findView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public RecycleHolder setTextColor(int id,String color){
        TextView view = findView(id);
        view.setTextColor(Color.parseColor(color));
        return  this;
    }

    public RecycleHolder setBackColor(int id,int color){
        View view = findView(id);
        view.setBackgroundColor(color);
        return  this;
    }

    public RecycleHolder setCheckedChangeLister(int id,CompoundButton.OnCheckedChangeListener listener){
        CheckBox view = (CheckBox)findView(id);
        view.setOnCheckedChangeListener(listener);
        return  this;
    }

    public RecycleHolder setChecked(int id,boolean b){
        CheckBox view = (CheckBox)findView(id);
        view.setChecked(b);
        return  this;
    }





}
