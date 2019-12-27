package com.nitjsr.culfest20.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nitjsr.culfest20.BuildConfig;
import com.nitjsr.culfest20.R;
import com.onesignal.OneSignal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DBCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbcheck);
        dbCheck();
        firstTimeCheck();
    }

    private void firstTimeCheck() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if (!pref.getBoolean("First", false)) {
            SharedPreferences.Editor editor = pref.edit();
            OneSignal.sendTag("mega_events", "true");
            OneSignal.sendTag("dance","true");
            OneSignal.sendTag("vocals","true");
            OneSignal.sendTag("quiz","true");
            OneSignal.sendTag("fine_arts","true");
            OneSignal.sendTag("fashion","true");
            OneSignal.sendTag("dramatics","true");
            OneSignal.sendTag("photography","true");
            OneSignal.sendTag("literary","true");
            OneSignal.sendTag("informals", "true");
            editor.putBoolean("First", true);
            editor.apply();
        }
    }

    private void dbCheck() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("appurl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String url = dataSnapshot.getValue(String.class);
                reference.child("Version").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            int version = dataSnapshot.getValue(Integer.class);
                            if (version > BuildConfig.VERSION_CODE) {
                                MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(DBCheckActivity.this);
                                alertDialog.setTitle("Update your App");
                                alertDialog.setMessage("Your app is out of date!");
                                alertDialog.setCancelable(false);
                                alertDialog.setPositiveButton(
                                        "UPDATE",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                                intent.setData(Uri.parse(url));
                                                startActivity(intent);
                                                System.gc();
                                                System.exit(0);
                                            }
                                        });
                                alertDialog.show();
                            } else {
                                maintenanceCheck();
                            }
                        } catch (Exception e) {
                            //
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void maintenanceCheck() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("maintenance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean x = dataSnapshot.getValue(Boolean.class);
                try {
                    if (x) {
                        MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(DBCheckActivity.this);
                        alertDialog.setTitle("Culfest '20");
                        alertDialog.setMessage("Server is under maintenance...");
                        alertDialog.setCancelable(false);
                        alertDialog.setPositiveButton(
                                "EXIT",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.gc();
                                        System.exit(0);
                                    }
                                });

                        alertDialog.show();
                    } else {
                        startActivity(new Intent(DBCheckActivity.this, MainActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                    }
                } catch (Exception e) {
                    //
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
