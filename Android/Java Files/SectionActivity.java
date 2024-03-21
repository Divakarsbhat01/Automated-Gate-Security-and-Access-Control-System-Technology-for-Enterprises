package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends AppCompatActivity {

    DatabaseReference mRef,dbref;
    String sel;
    private ListView mListv;

    String uid= new String();
    String date= new String();
    String time = new String();
    String section = new String();
    String branch = new String();
    String block = new String();
    String rfid = new String();
    String phnum = new String();

    List<Model> mList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        Intent intent=getIntent();
        sel= intent.getStringExtra("sec");
        mRef= FirebaseDatabase.getInstance().getReference("userdata");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Log.v("tag", "datass" + snapshot);
                    if(sel!= null){
                    if (snapshot.child("section").getValue().equals(sel)) {
                        Log.v("tag", "query" + sel);
                        getBranchdata(snapshot.getKey());
                    }
                   }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void getBranchdata(String key) {
        mListv = findViewById(R.id.mseclist);
        dbref= FirebaseDatabase.getInstance().getReference("/userdata").child(key);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uid =dataSnapshot.child("uid").getValue(String.class);
                date = dataSnapshot.child("date").getValue(String.class);
                time = dataSnapshot.child("time").getValue(String.class);
//                section = dataSnapshot.child("section").getValue(String.class);
//                block = dataSnapshot.child("block").getValue(String.class);
//                branch = dataSnapshot.child("branch").getValue(String.class);
//                rfid = dataSnapshot.child("rfid").getValue(String.class);
//                phnum = dataSnapshot.child("phnum").getValue(String.class);

                Model model=new Model();

                model.setUid(uid);
                model.setDate(date);
                model.setTime(time);
//                model.setBloxk(block);
//                model.setBranch(branch);
//                model.setRfid(rfid);
//                model.setSection(section);
//                model.setPhnum(phnum);


                mList.add(model);
                Log.v("ta","datafin"+mList);


                Myadaptr myadaptr=new Myadaptr(SectionActivity.this,mList);
                mListv.setAdapter(myadaptr);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}