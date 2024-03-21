package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    EditText mUid, mName, mBlock, mRid, mPassword, mpnum;
    Button mReg;
    Spinner mbranch, mschool, msection;
    String selected, selb, sels;

    private static FirebaseUser currentUser;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    String currentDate,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUid =findViewById(R.id.uid);
        mName = findViewById(R.id.name);
        // mBranch = findViewById(R.id.branch);
        //  mSchool = findViewById(R.id.school);
         mPassword = findViewById(R.id.password);
        mBlock = findViewById(R.id.block);
        mRid = findViewById(R.id.rfid);
        mReg= findViewById(R.id.regb);
        mschool= findViewById(R.id.spinschool);
        mbranch= findViewById(R.id.spinbranch);
        msection= findViewById(R.id.spinsection);
        mpnum = findViewById(R.id.pnum);


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf1= new SimpleDateFormat("hh:mm");
        currentDate = sdf.format(new Date());
        time= sdf1.format(new Date());
        Log.v("curent","datetime"+time);

        final String[] classtype={"Select School","Engineering","Mba","Law"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, classtype);

        mschool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected=classtype[i];
                Toast.makeText(RegisterActivity.this,selected, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mschool.setAdapter(arrayAdapter);

        final String[] branch={"Select Branch","CSE","ECE","MECH","Marketing","Finance","Hr","BC","Cd","Er"};
        ArrayAdapter arrayAdapter1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, branch);

        mbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selb=branch[i];
                Toast.makeText(RegisterActivity.this,selb, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mbranch.setAdapter(arrayAdapter1);

        final String[] section={"Select Section","E1","E2","E3","E4","E5","E6","L1","L2","L3","L4","L5","L6","M1","M2","M3","M4","M5","M6"};
        ArrayAdapter arrayAdapter2=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, section);

        msection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sels=section[i];
                Toast.makeText(RegisterActivity.this,sels, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        msection.setAdapter(arrayAdapter2);



        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUid.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"uid is null",Toast.LENGTH_SHORT).show();
                }

                if(mName.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"namee is null",Toast.LENGTH_SHORT).show();
                }

                if(selected.equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"branch is null",Toast.LENGTH_SHORT).show();
                }

                if(selb.equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"school is null",Toast.LENGTH_SHORT).show();
                }

                if(sels.equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"section is null",Toast.LENGTH_SHORT).show();
                }

                if(mBlock.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"block is null",Toast.LENGTH_SHORT).show();
                }

                if(mRid.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"ridf is null",Toast.LENGTH_SHORT).show();
                }

                if(mpnum.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"phone number is null",Toast.LENGTH_SHORT).show();

                }

                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                dbRef = FirebaseDatabase.getInstance().getReference("/userdata").child(mName.getText().toString());
                Log.d("Tag","userma" +dbRef);

                dbRef.child("uid").setValue(mUid.getText().toString());
                dbRef.child("uname").setValue(mName.getText().toString());
                dbRef.child("password").setValue(mPassword.getText().toString());
                dbRef.child("branch").setValue(selb);
                dbRef.child("school").setValue(selected);
                dbRef.child("section").setValue(sels);
                dbRef.child("block").setValue(mBlock.getText().toString());
                dbRef.child("rfid").setValue(mRid.getText().toString());
                dbRef.child("date").setValue(currentDate);
                dbRef.child("time").setValue(time);
                dbRef.child("phnum").setValue(mpnum.getText().toString());
                Toast.makeText(RegisterActivity.this,"Registered!!",Toast.LENGTH_SHORT).show();

                mUid.setText("");
                mRid.setText("");
                mBlock.setText("");
                mPassword.setText("");
                mpnum.setText("");
//                mBranch.setText("");
                mName.setText("");

            }
        });


    }

}