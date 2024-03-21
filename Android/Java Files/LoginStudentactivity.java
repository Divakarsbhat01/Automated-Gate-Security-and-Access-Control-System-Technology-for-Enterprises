package com.example.rfida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginStudentactivity extends AppCompatActivity {
EditText mName, mPAssword;
Button mLogin,btn4;
String nm,ps;
String Dnam, Dpass;

DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_studentactivity);
        mName = findViewById(R.id.user_name);
        mPAssword = findViewById(R.id.pass_word);
        mLogin = findViewById(R.id.login_stu);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm= mName.getText().toString();
                ps= mPAssword.getText().toString();
                if(nm.equals("admin")&&ps.equals("admin"))
                {
                    Intent intent=new Intent(LoginStudentactivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                if(nm.equals(""))
                {
                    Toast.makeText(LoginStudentactivity.this,"username is null",Toast.LENGTH_SHORT).show();
                }
                if(ps.equals(""))
                {
                    Toast.makeText(LoginStudentactivity.this,"password is null",Toast.LENGTH_SHORT).show();
                }

                dbref = FirebaseDatabase.getInstance().getReference("userdata");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                          Dnam = snapshot.child("uname").getValue(String.class);
                          Dpass = snapshot. child("password").getValue(String.class);
                          compareandLogin(Dnam,Dpass);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void compareandLogin(String dnam, String dpass) {
        if( nm.equals(dnam) && ps.equals(dpass))
        {
            Intent intent = new Intent(LoginStudentactivity.this, DetailsActivity.class);
            intent.putExtra("uname",nm);
            startActivity(intent);
        }
    }
}