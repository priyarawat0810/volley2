package com.example.volleyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.toolbox.StringRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn)
    public void onBtnClicked(View view){
        Intent intent =new Intent(MainActivity.this,JSonArrayRequest.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn1)
    public void onBtn1Clicked(View view){
        Intent intent=new Intent(MainActivity.this, SimpleRequestActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn2)
    public void onBtn2Clicked(View view){
        Intent intent=new Intent(MainActivity.this,JsonObjectActivity.class);
        startActivity(intent);
    }

}
