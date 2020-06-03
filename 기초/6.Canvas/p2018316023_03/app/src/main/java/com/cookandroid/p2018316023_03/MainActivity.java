package com.cookandroid.p2018316023_03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECT = 3;    //내부에서 쓰기위해 static으로 쓰는 것
    static int curShape = LINE;
    static  int curColor = Color.BLACK;
    static boolean finish = false; //
    static List<Shapes> shapes = new ArrayList<Shapes>(); //그린 도형을 arrarylist에 저장
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));   //캔버스로 쓸 임의의 뷰를 생성
        setTitle("그래픽 툴");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  //item 선택시
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.item1 :
                curShape = LINE;
                break;
            case R.id.item2 :
                curShape = CIRCLE;
                break;
            case R.id.item3 :
                curShape = RECT;
                break;
            case R.id.item4 :
                curColor = Color.RED;
                break;
            case R.id.item5 :
                curColor = Color.GREEN;
                break;
            case R.id.item6 :
                curColor = Color.BLUE;
                break;
            case R.id.item7 :
                savePicture(bitmap);

        }
        return  true;
    }
    //사진 저장
    private void savePicture(Bitmap bitmap) {
        try {
            FileOutputStream fos = openFileOutput("picture.png",MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
            Toast.makeText(this,"저장 완료",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //도형 모양, 죄표. 색
    private static class Shapes {
        int shapeType;
        int startX, startY, endX, endY;
        int color;
    }

    private class MyView extends View {
        int startX = -1, startY = -1, endX = -1, endY = -1;
        public MyView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {    //화면 터치시 이벤트 발생
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    finish = false;
                    break;
                case MotionEvent.ACTION_MOVE:   // 화면 계속 터치중
                    endX = (int) event.getX();
                    endY = (int) event.getY();
                    finish = false;
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP: //화면 터치가 끝나는 순간 그림이 그려짐
                    Shapes shape = new Shapes(); //객체 생성
                    shape.shapeType = curShape; //↓도형 정보 저장
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.endX = endX;
                    shape.endY = endY;
                    shape.color = curColor;
                    shapes.add(shape); //shape에 추가
                    finish = true;
                    this.invalidate();
                    break;
            }
            return true;
        }
        @Override
        protected void onDraw(Canvas canvas) {  //캔버스 준비
            super.onDraw(canvas);
            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(10);   //붓 굵기
            paint.setStyle(Paint.Style.STROKE); //선이 그려지도록
            paint.setColor(curColor);   //디폴트는 검정색으로 정했음
            for (int i=0; i<shapes.size();i++) {  //그린 도형을 불러와 그려놈
                Shapes shape = shapes.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType) {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.endX, shape.endY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.endX - shape.startX, 2) + Math.pow(shape.endY - shape.startY, 2));   //스퀘어루트 약자_피타고라스
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        break;
                    case RECT:
                        canvas.drawRect(shape.startX, shape.startY, shape.endX, shape.endY, paint);
                        break;
                }
            }
            //계속 도형을 그림
            if(finish == false){
                paint.setColor(curColor);
                switch (curShape){
                    case LINE:
                        canvas.drawLine(startX,startY,endX,endY,paint);
                        break;
                    case CIRCLE:
                        int radius = (int)Math.sqrt(Math.pow(endX-startX, 2)+Math.pow(endY-startY, 2));   //스퀘어루트 약자_피타고라스
                        canvas.drawCircle(startX,startY,radius,paint);
                        break;
                    case RECT:
                        canvas.drawRect(startX,startY,endX,endY,paint);
                        break;
                }
            }
        }
    }
}
