/**
 * Created By Srihari Manickam
 */

package com.datiustanzcorp.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.datiustanzcorp.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mainUserActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    //private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        final EditText name = (EditText)findViewById(R.id.name_field_editText);
        final EditText date_box = (EditText)findViewById(R.id.date_field_editText);

        myRef=FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name.setText(dataSnapshot.child("name").child("name").getValue().toString());
                 Date date = new Date();
                    SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy/MM/dd-HH:mm"); // currently date n time not in db
                    String formattedDate=""+FORMATTER.format(date);
                //Date r =new Date();
                    date_box.setText(formattedDate);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        /*
        *
        *
        *
        *
        *
        * code for the graph from azure lol!!
        *
        *
        *
        *
        *
        * */
        Button maps = (Button)findViewById(R.id.go_to_map_button);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainUserActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("name");*/




    }
}
