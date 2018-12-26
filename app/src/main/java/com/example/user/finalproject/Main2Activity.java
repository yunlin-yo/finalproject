package com.example.user.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
implements View.OnClickListener{
    TextView tv,tvs,tvd,tbug;
    ImageView iv1;

    class gopher{
        int positiotn; //地鼠位置
        int durtime;  //地鼠出現時間 到0消失
        gopher(int positiotn,int durtime){
            this.positiotn=positiotn;
            this.durtime=durtime;
        }
    }
    ImageView[] iv;
    public static int score=0; //分數
    gopher[] gopher={new gopher(-1,0),new gopher(-1,0)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //緊急跳回主頁
        tbug=(TextView)findViewById(R.id.tbug);
        tbug.setOnClickListener(this);

        //接收難度  顯示
        tvd=(TextView)findViewById(R.id.tvd);
        Bundle b=this.getIntent().getExtras();
        int level=b.getInt("d");
        switch (level){
            case 0:
                tvd.setText("簡單");
                level=1000;
                break;
            case 1:
                tvd.setText("普通");
                level=500;
                break;
            case 2:
                tvd.setText("困難");
                level=300;
                break;
        }

        //連接所有imageview
        tvs=(TextView)findViewById(R.id.tvs);
        iv=new ImageView[9];
        iv[0]=findViewById(R.id.iv0);
        iv[1]=findViewById(R.id.iv1);
        iv[2]=findViewById(R.id.iv2);
        iv[3]=findViewById(R.id.iv3);
        iv[4]=findViewById(R.id.iv4);
        iv[5]=findViewById(R.id.iv5);
        iv[6]=findViewById(R.id.iv6);
        iv[7]=findViewById(R.id.iv7);
        iv[8]=findViewById(R.id.iv8);

        for(int i=0;i<iv.length;i++)
        {
            final  int index=i;
            iv[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    click(index);
                }
            });
        }


        //倒數計時器
        tv=(TextView)findViewById(R.id.time);
        new CountDownTimer(30000,level){
            @Override
            public void onTick(long mi) {
                tv.setText("時間:"+mi/1000); //顯示時間
                for(int i=0;i< gopher.length;i++){
                    if(gopher[i].positiotn!=-1){
                        gopher[i].durtime--;        //將地鼠持續時間-1
                        if(gopher[i].durtime==0){
                            iv[gopher[i].positiotn].setImageDrawable(getResources().getDrawable(R.drawable.hole4)); //空洞圖
                            gopher[i].positiotn=-1;
                        }
                    }
                }
                //地鼠空了再給亂數
                for (int i =0;i<gopher.length;i++){
                    if(gopher[i].positiotn==-1){
                        int ran=0;
                        boolean same=true;
                        while (same){
                            ran=(int)(Math.random()*iv.length); //取亂數
                            same=false;
                            for(int j=0;j<gopher.length;j++){
                                if(i==j)continue;
                                if(ran==gopher[j].positiotn){
                                    same=true;
                                    break;
                                }
                            }
                        }
                        gopher[i].positiotn=ran;
                        gopher[i].durtime=2;
                        iv[gopher[i].positiotn].setImageDrawable(getResources().getDrawable(R.drawable.gopherz1)); //有地鼠的圖
                    }
                }
            }
            @Override
            public void onFinish() {
                String re;
                tv.setText("時間:Done!");
               if(score>=50){
                    re="太強了!";
               }else if(15<score&&score<50){
                   re="還不錯!";
               }else{
                    re="很爛欸!";
               }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Main2Activity.this);
                alertDialog.setTitle("遊戲結束");
                alertDialog.setMessage("分數:"+score+"\n"+re);
                alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertDialog.show();
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        finish();
    }
    private  void click(final  int index){
        for (int i=0;i<gopher.length;i++){
            if(gopher[i].positiotn==index){
                iv[index].setImageDrawable(getResources().getDrawable(R.drawable.hole4)); //被打到的圖
                gopher[i].positiotn=-1;
                score++;
                tvs.setText(Integer.toString(score));
            }
        }
    }
}

