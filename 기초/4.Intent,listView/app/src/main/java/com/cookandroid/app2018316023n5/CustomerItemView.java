package com.cookandroid.app2018316023n5;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerItemView extends LinearLayout {
    TextView tv1, tv2, tv3, tv4;
    ImageView iv;
    EditText et1, et2, et3, et4;
    Button btn;
    public CustomerItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) { //생성
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customer, this, true);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        iv = findViewById(R.id.imageView);
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        et4 = findViewById(R.id.editText4);
        btn = findViewById(R.id.button2);
    }
    public void setCustomerName(String name){ //이름
        et1.setText(name);
    }
    public void setOrderItem(String item){ //주문항목
        et2.setText(item);
    }
    public void setOrderSum(String sum){ //수량
        et3.setText(sum);
    }
    public void setOrderDay(String day){ //주문일자
        et4.setText(day);
    }
    public void setImage(int orderID){ // 이미지
        iv.setImageResource(orderID);
    }
}
