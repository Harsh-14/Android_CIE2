package com.fantappstic.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    TextView reg;
    EditText name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null && ( mAuth.getCurrentUser().isEmailVerified())){
            startActivity(new Intent(this, MainActivity.class));
        }

        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.username_input_reg);
        email=findViewById(R.id.useremail_input_reg);
        password=findViewById(R.id.pass_reg);

        reg=findViewById(R.id.regbtn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            String uss=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("Name",name.getText().toString());
                                            user.put("Email", email.getText().toString());
                                            user.put("Password", password.getText().toString());

                                            db.collection("users").document(uss)
                                                    .set(user)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                                            Log.d("harsh", "DocumentSnapshot successfully written!");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w("harsh", "Error writing document", e);
                                                        }
                                                    });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("harsh", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });



                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("harsh", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}