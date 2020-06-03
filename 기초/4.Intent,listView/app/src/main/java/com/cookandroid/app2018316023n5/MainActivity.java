package com.cookandroid.app2018316023n5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapter adapter;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("과제5");
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter();
        adapter.addItem(new CustomerItem("이영곤","볼펜","10","2015.4.15",R.drawable.lee));
        adapter.addItem(new CustomerItem("홍길동","컴퓨터","7","2015.4.15",R.drawable.hong));
        adapter.addItem(new CustomerItem("심청이","드레스","1","2015.4.15",R.drawable.sim));
        listView.setAdapter(adapter);
    }

    //고객어뎁터 생성_기본 어뎁터의 특징을 기반함
    class CustomAdapter extends BaseAdapter {
        ArrayList<CustomerItem> items = new ArrayList<CustomerItem>(); //아답터에 들어가는 리스트 항목들
        @Override
        public int getCount() {
            return items.size();    //아이템의 갯수의미
        }

        public void addItem(CustomerItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final CustomerItemView customerItemView = new CustomerItemView(getApplicationContext());
            final CustomerItem item = items.get(i);
            customerItemView.setCustomerName(item.getCustomerName());
            customerItemView.setOrderItem(item.getOrderItem());
            customerItemView.setOrderSum(item.getOrderSum());
            customerItemView.setOrderDay(item.getOrderDay());
            customerItemView.setImage(item.getOrderID());
            //인텐트로 넘길 값을 배열에 저장_ 주문자, 주문상품, 수량, 날짜
            final String order[] = {item.getCustomerName(), item.getOrderItem(), item.getOrderSum(), item.getOrderDay()};

            View v = customerItemView;
            if(v == null){
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.customer, null);
            }
            btn = v.findViewById(R.id.button2);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage(item.getCustomerName()+"님 "+item.getOrderItem()+" "+item.getOrderSum()+"개 "+item.getOrderDay()+"에 주문하신 거 맞나요?");
                    dlg.setPositiveButton("아니오",null);
                    dlg.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                            intent.putExtra("Order",order); //스트링 배열에 저장한 값을 인텐트로 보냄
                            startActivity(intent);
                        }
                    });
                    dlg.show();
                }
            });
            return customerItemView;
        }
    }
}
