package com.example.mati.nurplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener

{
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(this);

    }
    public void onClickButton1()
    {
        startActivity(new Intent("com.example.mati.nurplaner.Sac"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.sacActivity_BT):
                onClickButton1();
                break;
        }

    }
}
