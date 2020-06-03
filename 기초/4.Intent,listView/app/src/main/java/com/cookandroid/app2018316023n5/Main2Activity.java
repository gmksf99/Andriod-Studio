package com.cookandroid.app2018316023n5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("주문완료");
        iv = findViewById(R.id.imageView3);
        tv = findViewById(R.id.textView);
        Intent intent = getIntent();
        String[] order = intent.getStringArrayExtra("Order"); //넘겨받은 주문을 배열에 저장함
        switch (order[1]){
            case "볼펜":
                iv.setImageResource(R.drawable.pen);
                tv.setText(order[0]+"님이 "+order[1]+ order[2] + "를 "+order[3]+"에 주문하셨습니다. 감사합니다.");
                break;
            case "컴퓨터":
                iv.setImageResource(R.drawable.com);
                tv.setText(order[0]+"님이 "+order[1]+ order[2] + "를 "+order[3]+"에 주문하셨습니다. 감사합니다.");
                break;
            case "드레스":
                iv.setImageResource(R.drawable.dr);
                tv.setText(order[0]+"님이 "+order[1]+ order[2] + "를 "+order[3]+"에 주문하셨습니다. 감사합니다.");
                break;
        }

    }
}
