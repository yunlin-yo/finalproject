package com.example.user.finalproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
implements  View.OnClickListener{
    Button btn;
    RadioButton rb1,rb2,rb3;
    RadioGroup rg1;
    EditText et_name;
    int d;

    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup rg1=(RadioGroup)findViewById(R.id.rg1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        d=0;
                        break;
                    case R.id.rb2:
                        d=1;
                        break;
                    case  R.id.rb3:
                        d=2;
                        break;
                }
            }
        });
        btn = (Button) findViewById(R.id.btn_to2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i=new Intent();
            i.setClass(MainActivity.this,Main2Activity.class);
            Bundle b=new Bundle();
            b.putInt("d",d);
            i.putExtras(b);

            startActivity(i);
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}
