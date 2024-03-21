package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity
{
TextView mName, mschool, mbranch, msection, mblock, mrfid, mphnum;
String name,school, branch, section, block, rfid, phnum,ud;
DatabaseReference mref, mref1;
String getname;
     String   get1uid = new String();
     String get1date= new String();
     String get1time= new String();

    List<Model> mList =new ArrayList<>();
ListView l_view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();
        getname = intent.getStringExtra("uname");

        mName = findViewById(R.id.dbname);
        mschool = findViewById(R.id.dbschol);
        mbranch = findViewById(R.id.dbbranch);
        msection = findViewById(R.id.dbsection);
        mblock = findViewById(R.id.dbblock);
        mrfid = findViewById(R.id.dbrfid);
        mphnum = findViewById(R.id.dbphn);
        l_view = findViewById(R.id.lv);

        mref = FirebaseDatabase.getInstance().getReference("userdata").child(getname);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("uname").getValue(String.class);
                school = dataSnapshot.child("school").getValue(String.class);
                branch = dataSnapshot.child("branch").getValue(String.class);
                section = dataSnapshot.child("section").getValue(String.class);
                block = dataSnapshot.child("block").getValue(String.class);
                rfid = dataSnapshot.child("rfid").getValue(String.class);
                phnum = dataSnapshot.child("phnum").getValue(String.class);
                ud = dataSnapshot.child("uid").getValue(String.class);

                mName.setText(name);
                mschool.setText(school);
                mbranch.setText(branch);
                msection.setText(section);
                mblock.setText(block);
                mrfid.setText(rfid);
                mphnum.setText(phnum);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mref1 = FirebaseDatabase.getInstance().getReference("user_login");
        mref1.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.v("user","data"+snapshot);
                    Log.v("ud","dd"+ud);
                    if(snapshot.child("uid").getValue().equals(ud)){
                        Log.v("uid","data"+snapshot.child("uid").getValue().equals(ud));
                        get1uid = snapshot.child("uid").getValue(String.class);
                        get1date = snapshot.child("date").getValue(String.class);
                        get1time = snapshot.child("time").getValue(String.class);
                        Model model=new Model();

                        model.setUid(get1uid);
                        model.setDate(get1date);
                        model.setTime(get1time);

                        mList.add(model);
                        Log.v("ta","datafin"+mList);


                        Myadaptr myadaptr=new Myadaptr(DetailsActivity.this,mList);
                        l_view.setAdapter(myadaptr);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}