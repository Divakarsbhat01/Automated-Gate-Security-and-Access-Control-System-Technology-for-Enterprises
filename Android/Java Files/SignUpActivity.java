package com.example.rfida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText mUser,mPass,mPhnum,mUsername;
    private FirebaseAuth auth;
    String username;
    String phnum;
    Button mSignup;
    private static FirebaseUser currentUser;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    private String dbRefPath = "/userdata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseApp.initializeApp(SignUpActivity.this);
        mUser= findViewById(R.id.mail1);
        mPass= findViewById(R.id.passwrd1);
        mSignup= findViewById(R.id.login1);
        mPhnum=findViewById(R.id.phno);
        mUsername=findViewById(R.id.name1);



        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(SignupActivity.this,"did:"+did,Toast.LENGTH_LONG).show();

                final String email = mUser.getText().toString().trim();
                String password = mPass.getText().toString().trim();
                username = mUsername.getText().toString().trim();
                phnum = mPhnum.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phnum)) {
                    Toast.makeText(getApplicationContext(), "Enter phone num!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }






                auth = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                mSignup.setEnabled(true);

                                if (!task.isSuccessful())
                                {
                                    Toast.makeText(SignUpActivity.this, "Sign Up failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    writetodb(username,phnum,email);
                                    //    Toast.makeText(SignupActivity.this,"lid:"+did,Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(SignUpActivity.this, MainActivity.class);
//                                    intent1.putExtra("username",username);
//                                    intent1.putExtra("phnumU",phnum);
                                    startActivity(intent1);
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    public void writetodb( String username, String phnum, String mail){

        DatabaseReference.CompletionListener completionListener =
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(SignUpActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                };

        Log.d("Tag","user" +mail);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference("/Admindata").child(cleanEmail(mail));
        Log.d("Tag","userma" +dbRef);

        dbRef.child("name").setValue(username, completionListener);


        dbRef.child("phnum").setValue(phnum, completionListener);


    }

    private String cleanEmail(String originalEmail) {
        return originalEmail.replaceAll("\\.", ",");
    }
}