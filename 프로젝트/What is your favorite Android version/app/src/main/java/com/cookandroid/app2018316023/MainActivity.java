package com.cookandroid.app2018316023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    Switch sw;
    RadioGroup rg;
    RadioButton rbOreo, rbPie, rbQ;
    Button btnFin, btnReset;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("안드로이드 사진보기");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);  //상단바에 이미지 보이게 하는 코드
        tv1 = (TextView)findViewById(R.id.Text1);
        tv2 = (TextView)findViewById(R.id.Text2);
        sw = (Switch) findViewById(R.id.swAgree);
        rg = (RadioGroup)findViewById(R.id.RadioGroup);
        rbOreo = (RadioButton)findViewById(R.id.RbOreo);
        rbPie = (RadioButton)findViewById(R.id.RbPie);
        rbQ = (RadioButton)findViewById(R.id.RbQ);
        btnFin = (Button)findViewById(R.id.btnfin);
        btnReset = (Button)findViewById(R.id.btnreset);
        iv = (ImageView)findViewById(R.id.imageView);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {   //스위치 체크 유무
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(sw.isChecked() == true){
                    tv2.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    btnFin.setVisibility(View.VISIBLE);
                    btnReset.setVisibility(View.VISIBLE);
                }
                else {
                    tv2.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    btnFin.setVisibility(View.VISIBLE);
                    btnReset.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                }
            }
        });
        //해당 라디오버튼 클릭시 해당 이미지 출력
        rbOreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.oreo);
            }
        });
        rbPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.pie);
            }
        });
        rbQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.q);
            }
        });
        //화면 종료
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //처음 화면이 나오게 된다.
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sw.setChecked(false);               //switch 체크 해제
                rg.clearCheck();                    //라디오그룹 선택 해제
                tv2.setVisibility(View.INVISIBLE);
                rg.setVisibility(View.INVISIBLE);
                btnFin.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                iv.setVisibility(View.INVISIBLE);

            }
        });
    }
}
