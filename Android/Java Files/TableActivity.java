package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {
DatabaseReference mRef,dbref;
String sel,br;
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
        setContentView(R.layout.activity_table);

        Intent intent=getIntent();
        sel= intent.getStringExtra("sel");
        br = intent.getStringExtra("br");

        mRef= FirebaseDatabase.getInstance().getReference("userdata");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("tag","data"+dataSnapshot);

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.v("tag","datass"+snapshot.child("school").getValue());
                    if(snapshot.child("school").getValue().equals(sel)){
                        Log.v("tag","query"+sel);
                          getBranchdata(br,snapshot.getKey());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public  void getBranchdata(String br,String s){
        Log.v("tag","final"+s);
        mListv = findViewById(R.id.mlist1);
        dbref= FirebaseDatabase.getInstance().getReference("/userdata").child(s);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("branch").getValue().equals(br)){
                    Log.v("ta","datafin"+dataSnapshot);
                    uid =dataSnapshot.child("uid").getValue(String.class);
                    date = dataSnapshot.child("date").getValue(String.class);
                    time = dataSnapshot.child("time").getValue(String.class);
//                    section = dataSnapshot.child("section").getValue(String.class);
//                    block = dataSnapshot.child("block").getValue(String.class);
//                    branch = dataSnapshot.child("branch").getValue(String.class);
//                    rfid = dataSnapshot.child("rfid").getValue(String.class);
//                    phnum = dataSnapshot.child("phnum").getValue(String.class);

                   Model model=new Model();

                    model.setUid(uid);
                    model.setDate(date);
                    model.setTime(time);
//                    model.setBloxk(block);
//                    model.setBranch(branch);
//                    model.setRfid(rfid);
//                    model.setSection(section);
//                    model.setPhnum(phnum);


                    mList.add(model);
                    Log.v("ta","datafin"+mList);


                    Myadaptr myadaptr=new Myadaptr(TableActivity.this,mList);
                    mListv.setAdapter(myadaptr);



                }

                else if(dataSnapshot.equals(null)){
                    Toast.makeText(TableActivity.this,"no data found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}