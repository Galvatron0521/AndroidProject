package com.shdjrmyy.qgw.CompanyProject.ViewFolder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import com.shdjrmyy.qgw.CompanyProject.FollowUpPage.bean.Time;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

/**
 * Created by mindto on 2016/3/10.
 * 定义一个类，继承DialogFragment并实现TimePickerDialog.OnTimeSetListener实现监听
 */
public  class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //得到日期对象
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Toast.makeText(getActivity(), "当前时间设置为： " + hourOfDay + " : " + minute, Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new Time(hourOfDay+"",minute+""));
    }


}