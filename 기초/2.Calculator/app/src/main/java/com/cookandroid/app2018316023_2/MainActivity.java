package com.cookandroid.app2018316023_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnNam, btnPer, btnCancel, btnEqual;
    TextView tv;
    String n1, n2;
    Double result;
    String result2;
    Button[] numButtons = new Button[11];
    Integer[] numBtnIDs = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button15};
    Button[] numBtnAbi = new Button[6];
    Integer[] numBtnAbis = {R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15};
    int i;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        btnAdd = (Button) findViewById(R.id.button10);
        btnSub = (Button) findViewById(R.id.button11);
        btnMul = (Button) findViewById(R.id.button12);
        btnDiv = (Button) findViewById(R.id.button13);
        btnNam = (Button) findViewById(R.id.button14);
        btnPer = (Button) findViewById(R.id.button15);
        btnCancel = (Button) findViewById(R.id.button16);
        btnEqual = (Button) findViewById(R.id.button17);
        tv = (TextView) findViewById(R.id.textView);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (n1 != null) {
                    if (n2 != null) {
                        n1 = et1.getText().toString();
                        n2 = et2.getText().toString();
                        result = Double.parseDouble(n1) + Double.parseDouble(n2);
                    }
                }
                else {      //숫자를 입력안하고 연산자를 눌렸을때
                    Toast.makeText(getApplicationContext(), "숫자를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (n1 != null) {
                    if (n2 != null) {
                        n1 = et1.getText().toString();
                        n2 = et2.getText().toString();
                        result = Double.parseDouble(n1) - Double.parseDouble(n2);
                    }
                }
                return false;
            }
        });
        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (n1 != null) {
                    if (n2 != null) {
                        n1 = et1.getText().toString();
                        result = Double.parseDouble(n1) * Double.parseDouble(n2);
                    }
                }
                else {      //숫자를 입력안하고 연산자를 눌렸을때
                    Toast.makeText(getApplicationContext(), "숫자를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (n1 != null) {
                    if (n2 != null && n2 != "0") {
                        n1 = et1.getText().toString();
                        n2 = et2.getText().toString();
                        result = Double.parseDouble(n1) / Double.parseDouble(n2);
                    }
                }
                else {      //숫자를 입력안하고 연산자를 눌렸을때
                    Toast.makeText(getApplicationContext(), "숫자를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        btnNam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (n1 != null) {
                    if (n2 != null) {
                        n1 = et1.getText().toString();
                        n2 = et2.getText().toString();
                        result = Double.parseDouble(n1) / Double.parseDouble(n2);
                    }
                }
                else {      //숫자를 입력안하고 연산자를 눌렸을때
                    Toast.makeText(getApplicationContext(), "숫자를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(result != null) {            // 계산된 결과가 있으면 출력 = 연산자 입력했으면 출력
                    if (n2.trim().equals("0")) {
                        tv.setText("결과 : 0으로 나눌 수 없습니다.");
                    }
                    else {
                        tv.setText("결과 : " + result.toString());
                    }
                }
                else {                      // 숫자는 입력했으나 연산자를 입력 안했다면
                    Toast.makeText(getApplicationContext(), "연산자를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.getText().clear();
                et2.getText().clear();
                n1 = null;
                n2 = null;
                result = null; //결과 초기화
                tv.setText("결과 : ");
            }
        });

        for (i =0; i < numBtnIDs.length; i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for (i=0; i < numBtnIDs.length; i++) {
            final int index;    // 꼭 필요
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (et1.isFocused() == true) {
                        n1 = et1.getText().toString() + numButtons[index].getText().toString();
                        et1.setText(n1);
                    } else if (et2.isFocused() == true) {
                        n2 = et2.getText().toString() + numButtons[index].getText().toString();
                        et2.setText(n2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
