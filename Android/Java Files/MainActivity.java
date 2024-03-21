package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView m1Signup;
    EditText ed1;
    TextView ed2;
    Spinner mSpin,msp1;
    String selected, selbr, selsec,ddate;
    Button mProceed,mApply, mSectionbtn, mReg,btn3,btn2;
    Spinner mSpinSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpin=findViewById(R.id.spinC);
        msp1=findViewById(R.id.spinB);
        mSpinSec=findViewById(R.id.spinSec);
        mProceed=findViewById(R.id.btn_class);
        mApply=findViewById(R.id.btn_br);
        mSectionbtn= findViewById(R.id.btn_Sec);
        mReg = findViewById(R.id.reg_stu);
        m1Signup=findViewById(R.id.registe1);
        btn3=findViewById(R.id.button3);
        btn2=findViewById(R.id.button2);
        ed1=findViewById(R.id.editTextTextPersonNamex);
        ed1.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        ed2=findViewById(R.id.editTextDate2);
        ed2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int date=calendar.get(Calendar.DATE);
                String monthx=themonth(month);
                DatePickerDialog dpd=new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        String z1="";
                        if((dayOfMonth>0)&&(dayOfMonth<10))
                        {
                            z1="0"+String.valueOf(dayOfMonth);
                        }
                        else
                        {
                            z1=String.valueOf(dayOfMonth);
                        }
                    String z2=monthx;
                    String z3=String.valueOf(year);
                    ddate=z1+":"+z2+":"+z3;
                    ed2.setText(ddate);
                    }
                },year,month,date);
                dpd.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String xx=ed2.getText().toString();
                Intent intent=new Intent(MainActivity.this,dateact.class);
                intent.putExtra("date",xx);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String xx=ed1.getText().toString();
                Intent intent=new Intent(MainActivity.this,uidsearch.class);
                intent.putExtra("uid",xx);
                startActivity(intent);
            }
        });
        m1Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        final String[] classtype={"Engineering","Mba","Law"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, classtype);

        mSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected=classtype[i];
                Toast.makeText(MainActivity.this,selected, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mSpin.setAdapter(arrayAdapter);

        mProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Log.d("Tag","selected"+selected);

switch (selected){

    case "Engineering":
        msp1.setVisibility(View.VISIBLE);
                    final String[] brtype={"CSE","ECE","MECH"};
                    ArrayAdapter arrayAdapter1=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item, brtype);

                    msp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selbr=brtype[i];
                            Toast.makeText(MainActivity.this,selbr, Toast.LENGTH_LONG).show();
                            mApply.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    msp1.setAdapter(arrayAdapter1);
                    break;
    case "Mba":

        msp1.setVisibility(View.VISIBLE);
        final String[] brrtype={"Marketing","Finance","Hr"};
        ArrayAdapter arrayAdapter2=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item, brrtype);

        msp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selbr=brrtype[i];
                Toast.makeText(MainActivity.this,selbr, Toast.LENGTH_LONG).show();
                mApply.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        msp1.setAdapter(arrayAdapter2);
        break;


    case "Law":

        msp1.setVisibility(View.VISIBLE);
        final String[] brrrtype={"BC","Cd","Er"};
        ArrayAdapter arrayAdapter3=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item, brrrtype);

        msp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selbr=brrrtype[i];
                Toast.makeText(MainActivity.this,selbr, Toast.LENGTH_LONG).show();
                mApply.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        msp1.setAdapter(arrayAdapter3);
        break;

}
            }
        });

        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","sel"+selected);
                Log.d("tag","br"+selbr);

                Intent intent=new Intent(MainActivity.this,secact.class);
                intent.putExtra("br",selbr);
                startActivity(intent);

            }
        });


        String[] sectype= {"E1","E2","E3","E4","E5","E6","L1","L2","L3","L4","L5","L6","M1","M2","M3","M4","M5","M6"};
        ArrayAdapter adapt=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, sectype);
        mSpinSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selsec=sectype[i];
                Toast.makeText(MainActivity.this,selsec, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mSpinSec.setAdapter(adapt);

        mSectionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,test.class);

                Log.v("sec","ty"+selsec);
                intent.putExtra("sec",selsec);
                startActivity(intent);
            }
        });

       mReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
               startActivity(intent);
           }
       });

    }
    public String themonth(int month)
    {
        String[] monthnames={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        return monthnames[month];
    }
}