package com.cookandroid.app2018316023n04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    Button btn;
    View diglogView;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-6");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diglogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null); //diglog1을 작은 창으로 띄우겠다.
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                iv = (ImageView) diglogView.findViewById(R.id.imageView);
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        dig.setTitle("강아지");
                        iv.setImageResource(R.drawable.dog);
                        break;
                    case R.id.radioButton2:
                        dig.setTitle("고양이");
                        iv.setImageResource(R.drawable.cat);
                        break;
                    case R.id.radioButton3:
                        dig.setTitle("토끼");
                        iv.setImageResource(R.drawable.rabit);
                        break;
                    case R.id.radioButton4:
                        dig.setTitle("말");
                        iv.setImageResource(R.drawable.horse);
                        break;
                }
                dig.setView(diglogView);
                dig.setNegativeButton("닫기",null);
                dig.show();
            }
        });
    }
}
