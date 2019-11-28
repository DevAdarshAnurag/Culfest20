package com.nitjsr.culfest20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.User;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout tilName, tilInstitute, tilInstituteId, tilContact;
    TextInputEditText etName, etInstitute, etInstituteId, etContact;
    FirebaseUser firebaseUser;
    RadioGroup accomodation, tshirtSize;
    private int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        tilName = findViewById(R.id.til_name);
        tilInstitute = findViewById(R.id.til_institute);
        tilInstituteId = findViewById(R.id.til_id);
        tilContact = findViewById(R.id.til_contact);

        etName = findViewById(R.id.et_name);
        etInstitute = findViewById(R.id.et_in);
        etInstituteId = findViewById(R.id.et_iid);
        etContact = findViewById(R.id.et_contact);
        etName.setText(firebaseUser.getDisplayName());
        etInstitute.setText("NIT Jamshedpur");

        accomodation = findViewById(R.id.accomodation);
        tshirtSize = findViewById(R.id.tShirt);


        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString().trim();
                final String institute = etInstitute.getText().toString().trim();
                final String instituteId = etInstituteId.getText().toString().trim();
                final String contact = etContact.getText().toString().trim();
                final String email = firebaseUser.getEmail();
                final String uid = firebaseUser.getUid();
                boolean acc = true;
                if (accomodation.getCheckedRadioButtonId() == R.id.acc_yes) {
                    acc = true;
                } else if (accomodation.getCheckedRadioButtonId() == R.id.acc_no) {
                    acc = false;
                }
                String tShirt = "";
                if (tshirtSize.getCheckedRadioButtonId() == R.id.ts) {
                    tShirt = "S";
                } else if (tshirtSize.getCheckedRadioButtonId() == R.id.tm) {
                    tShirt = "M";
                } else if (tshirtSize.getCheckedRadioButtonId() == R.id.tl) {
                    tShirt = "L";
                } else if (tshirtSize.getCheckedRadioButtonId() == R.id.txl) {
                    tShirt = "XL";
                } else if (tshirtSize.getCheckedRadioButtonId() == R.id.txxl) {
                    tShirt = "XXL";
                }
                if (validate(name, institute, instituteId, contact)) {
                    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Counter");
                    final boolean finalAcc = acc;
                    final String finalTShirt = tShirt;
                    dbRef.runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            Integer CurrentValue = mutableData.getValue(Integer.class);
                            if (CurrentValue != null) {
                                cid = CurrentValue + 1;
                                mutableData.setValue(CurrentValue + 1);
                            } else {
                                mutableData.setValue(1);
                            }
                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                            String culfestId = "CFA";
                            if (cid < 10)
                                culfestId = culfestId + "000" + cid;
                            else if (cid < 100)
                                culfestId = culfestId + "00" + cid;
                            else if (cid < 1000)
                                culfestId = culfestId + "0" + cid;
                            else
                                culfestId = culfestId + cid;
                            User user = new User(name, institute, instituteId, contact, finalAcc,false,false,false, finalTShirt, uid, email, culfestId);
                            reference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegisterActivity.this, DBCheckActivity.class));
                                    finish();
                                }
                            });
                        }
                    });


                }
            }
        });
    }

    private boolean validate(String name, String institute, String instituteId, String contact) {

        boolean flag = true;
        if (name.equals("")) {
            flag = false;
            tilName.setError("Mandatory Field");
        }
        if (institute.equals("")) {
            flag = false;
            tilInstitute.setError("Mandatory Field");
        }
        if (instituteId.equals("")) {
            flag = false;
            tilInstituteId.setError("Mandatory Field");
        }
        if (contact.equals("")) {
            flag = false;
            tilContact.setError("Mandatory Field");
        }
        return flag;
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        googleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(RegisterActivity.this, SplashActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                    }
                });
    }
}
