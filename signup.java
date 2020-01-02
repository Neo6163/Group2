/*Created By Srihari Manickam*/
package com.datiustanzcorp.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.datiustanzcorp.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText full_name = (EditText) findViewById(R.id.full_name_editText);
        final EditText age = (EditText) findViewById(R.id.age_editText);
        final EditText Email = (EditText) findViewById(R.id.email_editText);
        final EditText password = (EditText) findViewById(R.id.password_editText);
        final EditText Application_id = (EditText) findViewById(R.id.Application_id_editText);
        final Button signin = (Button)findViewById(R.id.signin_button);
        mAuth = FirebaseAuth.getInstance();
                signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(Email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    HashMap<String,String> as=new HashMap<>();
                                    as.put("name",full_name.getText().toString());
                                    as.put("age",age.getText().toString());
                                    as.put("Application_id",Application_id.getText().toString());
                                    FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid()).child("name").setValue(as);
                                    Intent intent = new Intent(signup.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(signup.this,"failed--->try Again!!",Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });
            }
        });


    }
}
