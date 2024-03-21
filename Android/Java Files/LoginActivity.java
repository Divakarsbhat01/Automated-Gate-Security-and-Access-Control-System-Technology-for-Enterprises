package com.example.rfida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    TextView mForgot/*mSignup*/;
    Button mLogin;
    EditText mUser,mPass;
    String name,phnum;
    String email;
    String password;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(LoginActivity.this);
//        mForgot=findViewById(R.id.forgot);
       /* mSignup=findViewById(R.id.registe);*/
        mLogin=findViewById(R.id.logi);
        mUser=findViewById(R.id.username);
        mPass=findViewById(R.id.pass);

        /*mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });*/

        mLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email = mUser.getText().toString();
                password = mPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                //authenticate user
                auth=FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Incorrect Email ID/Password", Toast.LENGTH_SHORT).show();
                                } else {
                                    DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                            if (databaseError != null) {
                                                Toast.makeText(LoginActivity.this, "Error Signing In", Toast.LENGTH_SHORT).show();

                                            }


                                        }
                                    };
//
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    intent.putExtra("sak",mDeviceId);
//                                    intent.putExtra("customernam",name);
//                                    intent.putExtra("customernum",phnum);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
//

                                }

                            }

                        });

            }
        });

    }
}