package com.cookandroid.p2018316023_n06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3;
    TextView tv;
    Button btn1, btn2;
    MyDBHelper myDBHelper;
    SQLiteDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("KPU 조회");
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        tv = findViewById(R.id.textView4);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        myDBHelper = new MyDBHelper(this);  //데이터 베이스

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // 입력
                db = myDBHelper.getWritableDatabase();
                ContentValues row = new ContentValues();
                row.put("dname", et1.getText().toString()); // 데이터 채워줌
                row.put("numstudent", Integer.parseInt(et2.getText().toString()));
                row.put("contact", et3.getText().toString());
                db.insert("department",null,row);
                db.close();
                tv.setText("입력 성공");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //조회
                db =myDBHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from department", null);
                String result="";   //임의의 결과 지정
                while(cursor.moveToNext()){
                    result += (cursor.getInt(0)+" : "+cursor.getString(1)+" : "
                            +cursor.getInt(2)+" : "+cursor.getString(3)+"\n");
                }
                tv.setText(result);
                cursor.close(); //반드시 닫아줘야 에러가 안남
                db.close();
            }
        });
    }

    public class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "KPU", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {   //depid 자동생성
            db.execSQL("CREATE TABLE department (depid integer primary key autoincrement," + "dname text, numstudent integer, contact text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS department");
            onCreate(db);
        }
    }
}