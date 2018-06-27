package com.example.xkfeng.advancedactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by initializing on 2018/6/27.
 */

public class MyViewFilpperActivity extends AppCompatActivity {


    private ListView lv_one ;
    private ListView lv_two ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.horizontalview);

        lv_one =  (ListView)findViewById(R.id.lv_one) ;
        lv_two = (ListView)findViewById(R.id.lv_two) ;
        String [] str1 = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8", "9" , "10" , "11" , "12" , "13" , "14" ,"15"} ;
        String [] str2 = {"A" , "B" ,"C" , "D" ,"E" ,"F" ,"G","H","I","J","K","L","M","N","O","P","Q"} ;
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this , R.layout.list_item , str1) ;
        ArrayAdapter arrayAdapter1 = new ArrayAdapter<String>(this ,R.layout.list_item , str2) ;
        lv_two.setAdapter(arrayAdapter1);
        lv_one.setAdapter(arrayAdapter);

    }
}
