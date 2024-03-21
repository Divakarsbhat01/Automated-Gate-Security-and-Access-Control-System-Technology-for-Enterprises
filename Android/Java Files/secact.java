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
public class secact extends AppCompatActivity {
    TextView mName, mschool, mbranch, msection, mblock, mrfid, mphnum;
    String name, school, branch, section, block, rfid, phnum, ud;
    DatabaseReference mref, mref1;
    String getname;
    String get1uid = new String();
    String get1date = new String();
    String get1time = new String();

    List<Model> mList = new ArrayList<>();
    ListView l_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secact);
        Intent intent=getIntent();
        branch= intent.getStringExtra("br");
        l_view2 = findViewById(R.id.lvxx);
        mref1 = FirebaseDatabase.getInstance().getReference("user_login");
        mref1.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.v("user","data"+snapshot);
                    Log.v("ud","dd"+ud);
                    if(snapshot.child("branch").getValue().equals(branch)){
                        Log.v("branch","data"+snapshot.child("branch").getValue().equals(branch));
                        get1uid = snapshot.child("uid").getValue(String.class);
                        get1date = snapshot.child("date").getValue(String.class);
                        get1time = snapshot.child("time").getValue(String.class);
                        Model model=new Model();

                        model.setUid(get1uid);
                        model.setDate(get1date);
                        model.setTime(get1time);

                        mList.add(model);
                        Log.v("ta","datafin"+mList);


                        Myadaptr myadaptr=new Myadaptr(secact.this,mList);
                        l_view2.setAdapter(myadaptr);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}