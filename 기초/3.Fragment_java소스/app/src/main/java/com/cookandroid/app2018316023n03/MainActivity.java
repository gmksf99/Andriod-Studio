package com.cookandroid.app2018316023n03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    MyTabFragment myFrags[] = new MyTabFragment[3];
    ActionBar.Tab ca, tp, pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        setTitle("과제 #3");

        ca = bar.newTab();
        ca.setText("캘린더");
        ca.setTabListener(this);
        bar.addTab(ca);

        tp = bar.newTab();
        tp.setText("타임피커");
        tp.setTabListener(this);
        bar.addTab(tp);

        pb = bar.newTab();
        pb.setText("프로그래스바");
        pb.setTabListener(this);
        bar.addTab(pb);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFragment = null;
        if(myFrags[tab.getPosition()]==null){
            myTabFragment = new MyTabFragment();    //세로 생성
            Bundle data = new Bundle(); //번들을 통해 데이터 전송
            data.putString("tabName", tab.getText().toString());
            myTabFragment.setArguments(data);   //프래그먼트로 데이터 전송
            myFrags[tab.getPosition()] = myTabFragment;
        }
        else{
            myTabFragment = myFrags[tab.getPosition()];
        }
        ft.replace(android.R.id.content, myTabFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyTabFragment extends androidx.fragment.app.Fragment{
        String tabName;
        int now;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");    //해당 데이터 꺼내옴
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {    //위젯을 리턴한다.
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);   //XML을 사용하지 않아서 파라미터를 만들어줌
            params.gravity = Gravity.CENTER;    //파라미터들 가운데로 정렬
            LinearLayout base = new LinearLayout(super.getActivity());
            base.setOrientation(LinearLayout.VERTICAL);
            base.setLayoutParams(params);   //파라미터를 파람즈로
            CalendarView cv = new CalendarView(getContext());
            TimePicker tpi = new TimePicker(getContext());
            final TextView tv1 = new TextView(getContext());    //날짜, 시간이 뜨는 텍스트
            tv1.setTextSize(20);
            tv1.setPadding(20,0,0,0);
            final ProgressBar pgb = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
            Button btn1 = new Button(getContext());
            Button btn2 = new Button(getContext());
            pgb.setPadding(10,20,10,10);
            pgb.setMax(100);
            pgb.setProgress(50);
            if (tabName=="캘린더") {
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        int m = i1+1;
                        tv1.setText("날짜 : "+i+"년"+m+"월"+i2+"일");
                    }
                });
                base.addView(cv);
                base.addView(tv1);
            }
            if (tabName=="타임피커") {
                tpi.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                        tv1.setText("시간 : "+i+"시"+i1+"분");
                    }
                });
                base.addView(tpi);
                base.addView(tv1);
            }
            if (tabName=="프로그래스바") {
                LinearLayout base2 = new LinearLayout(super.getActivity());
                base2.setLayoutParams(params);
                base2.setOrientation(LinearLayout.HORIZONTAL);  //Horizontal타입 레이아웃
                btn1.setText("감소");
                btn2.setText("증가");
                base2.setPadding(20,0,0,0);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        now = pgb.getProgress();
                        now -= 5;
                        pgb.setProgress(now);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        now = pgb.getProgress();
                        now += 5;
                        pgb.setProgress(now);
                    }
                });
                base.addView(pgb);
                base2.addView(btn1);
                base2.addView(btn2);
                base.addView(base2);    //base2에 저장한 화면을 base에 저장
            }
            return  base;
        }
    }
}
