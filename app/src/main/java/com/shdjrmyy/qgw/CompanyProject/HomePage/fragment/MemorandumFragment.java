package com.shdjrmyy.qgw.CompanyProject.HomePage.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.MonthPager;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.Base;
import com.shdjrmyy.qgw.CompanyProject.BaseFolder.BaseFragment;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.EditRecordActivity;
import com.shdjrmyy.qgw.CompanyProject.HomePage.activity.EventsDetailsActivity;
import com.shdjrmyy.qgw.CompanyProject.HomePage.adapter.EventAdapter;
import com.shdjrmyy.qgw.CompanyProject.HomePage.bean.BwlBean;
import com.shdjrmyy.qgw.CompanyProject.HomePage.bean.MemorandumBean;
import com.shdjrmyy.qgw.CompanyProject.HomePage.bean.MemorandumDaoUtils;
import com.shdjrmyy.qgw.CompanyProject.PatientPage.bean.ResultBean;
import com.shdjrmyy.qgw.CompanyProject.R;
import com.shdjrmyy.qgw.CompanyProject.UtilsFolder.GsonCallBack;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.CustomDayView;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.EditTextDialog;
import com.shdjrmyy.qgw.CompanyProject.ViewFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 16001 on 2017/10/18 0018.
 */

public class MemorandumFragment extends BaseFragment implements View.OnClickListener {

    private TextView textViewYearDisplay;
    private TextView textViewMonthDisplay;
    private TextView backToday;
    private CoordinatorLayout content;
    private MonthPager monthPager;
    private RecyclerView rvToDoList;
    private ImageView scrollSwitch;
    private TextView nextMonthBtn;
    private TextView lastMonthBtn;

    private ArrayList<com.ldf.calendar.view.Calendar> currentCalendars = new ArrayList<>();
    private List<MemorandumBean.DataBean.ListBean> listBeans = new ArrayList<>();
    private List<BwlBean> bwlBeans = new ArrayList<>();
    private CalendarViewAdapter calendarAdapter;
    private OnSelectDateListener onSelectDateListener;
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;
    private Context context;
    private CalendarDate currentDate;
    private boolean initiated = false;
    private AlertDialog alertDialog;
    private MemorandumDaoUtils dataBase;
    private SimpleDateFormat sdf;
    private FloatingActionButton actionButton;
    private View.OnTouchListener otl;
    private EventAdapter eventAdapter;

    private long end;
    private long start;
    private String title;
    private int calendarid;
    private String calendardescribe;

    @Override
    protected int setLayout() {
        return R.layout.fragemnt_memoranduml;
    }

    @Override
    protected void initView(View view) {
        dataBase = new MemorandumDaoUtils(getContext());
        sdf = new SimpleDateFormat("hh:mm:ss");
        context = getContext();
        content = view.findViewById(R.id.content);
        monthPager = view.findViewById(R.id.calendar_view);
        monthPager.setViewheight(Utils.dpi2px(getContext(), 270));
        backToday = view.findViewById(R.id.top_jt);
        scrollSwitch = view.findViewById(R.id.top_ss);
        nextMonthBtn = view.findViewById(R.id.top_sy);
        lastMonthBtn = view.findViewById(R.id.top_xx);
        textViewMonthDisplay = view.findViewById(R.id.top_mounth);
        textViewYearDisplay = view.findViewById(R.id.top_year);
        rvToDoList = view.findViewById(R.id.list);
        actionButton = view.findViewById(R.id.ActionButton);
        actionButton.setOnClickListener(this);
        rvToDoList.setHasFixedSize(true);
        //这里用线性显示 类似于listview
        rvToDoList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvToDoList.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(getContext(), R.color.split_color)));
        initCurrentDate();
        initCalendarView();
        initToolbarClickListener();
        alertDialog = EditTextDialog.init(R.layout.dialog_ed2, getContext(), new EditTextDialog.CallBack() {
            @Override
            public void Confirm(BwlBean bean) {
                bean.setDay(currentDate.getDay());
                bean.setMouth(currentDate.getMonth());
                bean.setYear(currentDate.getYear());
                bean.setTime(sdf.format(new Date()));
                if (bean.getTitle().isEmpty() && bean.getContent().isEmpty()) {
                    Toast.makeText(context, "请将资料填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.insertMemorandum(bean);
                    initInsert();
                }
                refreshList();
                initMarkData();
            }
        });
        rvToDoList.setOnTouchListener(otl);

    }

    private void initInsert() {
        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
        int index = list.size() - 1;
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "insertOrUpdateCalendar")
                .addParams("data", new insertOrUpdateCalendar("insert", Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID(),
                        list.get(index).getTitle(), list.get(index).getContent(), list.get(index).getStart(), list.get(index).getEnd()).toJson())
                .build().execute(new GsonCallBack<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) throws JSONException {
                String status = response.getStatus();
                if ("0".equals(status)) {
                    initData();
                    Toast.makeText(context, response.getData().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.getData().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    public void refreshList() {
        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
        eventAdapter = new EventAdapter(R.layout.item_events, list);
        rvToDoList.setAdapter(eventAdapter);
        initClick();
    }

    private void initClick() {
        eventAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.content:
                        List<BwlBean> list = dataBase.queryMemorandumByDate(currentDate);
                        Intent intent = new Intent(getActivity(), EventsDetailsActivity.class);
                        intent.putExtra("title", list.get(position).getTitle());
                        intent.putExtra("Content", list.get(position).getContent());
                        intent.putExtra("Start", list.get(position).getStart());
                        intent.putExtra("End", list.get(position).getEnd());
                        startActivity(intent);
                        break;
                    case R.id.right:
                        List<BwlBean> listDelete = dataBase.queryMemorandumByDate(currentDate);
                        dataBase.deleteMemorandum(listDelete.get(position));
                        refreshList();
                        break;
                    case R.id.left:
                        List<BwlBean> list1 = dataBase.queryMemorandumByDate(currentDate);
                        Intent intent1 = new Intent(getActivity(), EditRecordActivity.class);
                        intent1.putExtra("Calendarid", list1.get(position).get_id());
                        intent1.putExtra("title", list1.get(position).getTitle());
                        intent1.putExtra("Content", list1.get(position).getContent());
                        intent1.putExtra("Start", list1.get(position).getStart());
                        intent1.putExtra("End", list1.get(position).getEnd());
                        startActivityForResult(intent1, 0);
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            initData();
        }
    }

    @Override
    protected void initData() {
        refreshList();
        initMarkData();
        listBeans.clear();
        OkHttpUtils.post().url(Base.url)
                .addParams("act", "selectCalendarList")
                .addParams("data", new selectCalendarList(Base.getMD5Str(), Base.getTimeSpan(), Base.getUserID()).toJson())
                .build().execute(new GsonCallBack<MemorandumBean>() {
            @Override
            public void onSuccess(MemorandumBean response) throws JSONException {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    MemorandumBean.DataBean.ListBean listBean = new MemorandumBean.DataBean.ListBean();
                    end = response.getData().getList().get(i).getEnd();
                    start = response.getData().getList().get(i).getStart();
                    title = response.getData().getList().get(i).getTitle();
                    calendarid = response.getData().getList().get(i).getCalendarid();
                    calendardescribe = response.getData().getList().get(i).getCalendardescribe();
                    listBean.setEnd(end);
                    listBean.setStart(start);
                    listBean.setTitle(title);
                    listBean.setCalendarid(calendarid);
                    listBean.setCalendardescribe(calendardescribe);
                    listBeans.add(listBean);
                }
                initMemorandum();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void initMemorandum() {
        dataBase.deleteAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        for (int i = 0; i < listBeans.size(); i++) {
            long start = listBeans.get(i).getStart();
            String startFormat = dateFormat.format(start);
            int daoYear = Integer.parseInt(startFormat.substring(0, startFormat.indexOf("年")));
            int daoMonth = Integer.parseInt(startFormat.substring(startFormat.indexOf("年") + 1, startFormat.indexOf("月")));
            int daoDay = Integer.parseInt(startFormat.substring(startFormat.indexOf("月") + 1, startFormat.indexOf("日")));
            BwlBean bwlBean = new BwlBean();
            bwlBean.setYear(daoYear);
            bwlBean.setMouth(daoMonth);
            bwlBean.setDay(daoDay);
            bwlBean.setStart(String.valueOf(listBeans.get(i).getStart()));
            bwlBean.setEnd(String.valueOf(listBeans.get(i).getEnd()));
            bwlBean.setTitle(listBeans.get(i).getTitle());
            bwlBean.setContent(listBeans.get(i).getCalendardescribe());
            bwlBean.set_id(Long.valueOf(listBeans.get(i).getCalendarid()));
            bwlBeans.add(bwlBean);
            dataBase.insertMultMemorandum(bwlBeans);
        }
    }

    @Override
    public String getTitle() {
        return "备忘录";
    }


    /**
     * 初始化对应功能的listener
     *
     * @return void
     */
    private void initToolbarClickListener() {
        backToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackToDayBtn();
            }
        });
        scrollSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendarAdapter.getCalendarType() == CalendarAttr.CalendayType.WEEK) {
                    nextMonthBtn.setText("下月");
                    lastMonthBtn.setText("上月");
                    Utils.scrollTo(content, rvToDoList, monthPager.getViewHeight(), 500);
                    ObjectAnimator.ofFloat(scrollSwitch, "rotation", 180f, 0f).setDuration(500).start();
                    calendarAdapter.switchToMonth();
                } else {
                    nextMonthBtn.setText("下周");
                    lastMonthBtn.setText("上周");
                    Utils.scrollTo(content, rvToDoList, monthPager.getCellHeight(), 500);
                    calendarAdapter.switchToWeek(monthPager.getRowIndex());
                    ObjectAnimator.ofFloat(scrollSwitch, "rotation", 0f, 180f).setDuration(500).start();
                }
            }
        });

        nextMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() + 1);
            }
        });
        lastMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthPager.setCurrentItem(monthPager.getCurrentPosition() - 1);
            }
        });
    }

    /**
     * 初始化currentDate
     *
     * @return void
     */
    private void initCurrentDate() {
        currentDate = new CalendarDate();
        textViewYearDisplay.setText(currentDate.getYear() + "年");
        textViewMonthDisplay.setText(currentDate.getMonth() + "月");
    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     *
     * @return void
     */
    private void initCalendarView() {
        initListener();
        CustomDayView customDayView = new CustomDayView(context, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                context,
                onSelectDateListener,
                CalendarAttr.CalendayType.MONTH,
                customDayView);
        initMarkData();
        initMonthPager();
    }

    /**
     * 初始化标记数据，HashMap的形式，可自定义
     *
     * @return void
     */
    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        List<BwlBean> lists = dataBase.queryAllRecord();
        for (BwlBean bean : lists) {
            markData.put(bean.getYear() + "-" + bean.getMouth() + "-" + bean.getDay(), "0");
        }
        calendarAdapter.setMarkData(markData);
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                refreshClickDate(date);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
    }

    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        textViewYearDisplay.setText(date.getYear() + "年");
        textViewMonthDisplay.setText(date.getMonth() + "月");
        refreshList();
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     * @return void
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) instanceof com.ldf.calendar.view.Calendar) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    textViewYearDisplay.setText(date.getYear() + "年");
                    textViewMonthDisplay.setText(date.getMonth() + "月");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onClickBackToDayBtn() {
        refreshMonthPager();
    }

    private void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarAdapter.notifyDataChanged(today);
        textViewYearDisplay.setText(today.getYear() + "年");
        textViewMonthDisplay.setText(today.getMonth() + "月");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActionButton:
                alertDialog.show();
                break;
        }
    }

    static class selectCalendarList {

        private String appKey;
        private String timeSpan;
        private int id;

        public selectCalendarList(String appKey, String timeSpan, int id) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.id = id;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    static class insertOrUpdateCalendar {

        private String optionTag;
        private String appKey;
        private String timeSpan;
        private int userID;
        private String title;
        private String calendardescribe;
        private String start;
        private String end;

        public insertOrUpdateCalendar(String optionTag, String appKey, String timeSpan, int userID,
                                      String title, String calendardescribe, String start, String end) {
            this.optionTag = optionTag;
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.userID = userID;
            this.title = title;
            this.calendardescribe = calendardescribe;
            this.start = start;
            this.end = end;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
