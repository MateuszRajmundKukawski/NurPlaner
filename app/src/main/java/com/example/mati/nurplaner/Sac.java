package com.example.mati.nurplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Sac extends AppCompatActivity implements OnItemSelectedListener, View.OnClickListener {

    Button calcButton;
    int cylinder;
    int startP;
    int endP;
    int avgD;
    int dTime;
    //    EditText text;
    EditText startP_ET;
    EditText endP_ET;
    EditText avgD_ET;
    EditText dTime_ET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sac);
        calcButton = (Button) findViewById(R.id.caclSacBt);
        calcButton.setOnClickListener(this);
        startP_ET = (EditText) findViewById(R.id.startP_TexEdit);
        endP_ET = (EditText) findViewById(R.id.endP_TexEdit);
        avgD_ET = (EditText) findViewById(R.id.avgD_TexEdit);
        dTime_ET = (EditText) findViewById(R.id.timeD_TexEdit);

        Spinner vCylListSpinner = (Spinner) findViewById(R.id.cilinderList);

        vCylListSpinner.setOnItemSelectedListener(this);

        List<String> cylinsers = new ArrayList<>();
        cylinsers.add("11");
        cylinsers.add("12");
        cylinsers.add("18");
        cylinsers.add("20");
        cylinsers.add("24");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cylinsers);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        vCylListSpinner.setAdapter(dataAdapter);
    }

//    private boolean isEmpty(EditText etText) {
//        return etText.getText().toString().trim().length() == 0;
//    }

    public void onClickCalcBt() {
//        String tmp = startP_ET.getText().toString();
//        if (isEmpty(startP_ET))
        startP = -1;
        endP =  -1;
        avgD =  -1;
        dTime =  -1;
        if (!TextUtils.isEmpty(startP_ET.getText())) {
//            Toast.makeText(getApplicationContext(), "PODAJ CISNIENIE POCZATKOWE", Toast.LENGTH_LONG).show();

//        startP = Integer.parseInt(startP_ET.getText().toString());
//        } else {
            startP = Integer.parseInt(startP_ET.getText().toString());
//            startP = 0;
        }
        if (!TextUtils.isEmpty(endP_ET.getText())) {
//            Toast.makeText(getApplicationContext(), "PODAJ CISNIENIE KONCOWE", Toast.LENGTH_LONG).show();
//        } else {
            endP = Integer.parseInt(endP_ET.getText().toString());

        }
        if (!TextUtils.isEmpty(avgD_ET.getText())) {
//            Toast.makeText(getApplicationContext(), "Podaj średnią glebokosc", Toast.LENGTH_LONG).show();
//        } else {
            avgD = Integer.parseInt(avgD_ET.getText().toString());

        }
        if (!TextUtils.isEmpty(dTime_ET.getText())) {
//            Toast.makeText(getApplicationContext(), "Podaje czas nurkowania", Toast.LENGTH_LONG).show();
//        } else {
            dTime = Integer.parseInt(dTime_ET.getText().toString());
        }
        if (startP <= endP) {
            Toast.makeText(getApplicationContext(), "Ciśnienie końcowe większe niż początkowe", Toast.LENGTH_LONG).show();
            startP = 0;
            endP = 0;
        }
        if (startP > 0 && endP > 0 && avgD > 0 && dTime > 0) {
            int mySac = getSac(cylinder, startP, endP, dTime, avgD);
            Toast.makeText(getApplicationContext(), "SAC: " + Integer.toString(mySac), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Popraw parametry", Toast.LENGTH_LONG).show();
        }
    }


    public int getSac(int vCyl, int pStart, int pEnd, int dTime, int avgD) {
        int sac;
        sac = ((pStart - pEnd) * vCyl / dTime) / (1 + avgD / 10);
        return sac;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Butla: " + item, Toast.LENGTH_LONG).show();
        cylinder = Integer.parseInt(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.caclSacBt):
                onClickCalcBt();
                break;
        }

    }
}
