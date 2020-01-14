package com.nitjsr.culfest20.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.EventChipAdapter;
import com.nitjsr.culfest20.models.Events;
import com.nitjsr.culfest20.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static Bitmap bitmap;
    FirebaseUser firebaseUser;
    TextView profileName, profileInstitute, profileId, profileEmail, profileTShirt;
    CircleImageView profileImage;
    ImageView profilePayment, qr;
    RelativeLayout progress;
    RecyclerView eventRecycler;

    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<Events> list=new ArrayList<Events>();
        list.add(new Events(R.drawable.ic_mega_events,R.color.colorPrimaryDark,"Mega Events","\u20B9 2,31,000"));
        list.add(new Events(R.drawable.ic_dance,R.color.fb,"Dance","\u20B9 60,600"));
        list.add(new Events(R.drawable.ic_vocals,R.color.insta,"Vocals","\u20B9 58,600"));
        list.add(new Events(R.drawable.ic_qunite,R.color.red,"Quiz","\u20B9 42,000"));
        list.add(new Events(R.drawable.ic_fine_arts,R.color.gold,"Fine Arts","\u20B9 50,800"));
        list.add(new Events(R.drawable.ic_fashion,R.color.colorAccent,"Fashion","\u20B9 70,000"));
        list.add(new Events(R.drawable.ic_dramatics,R.color.colorPrimaryDark,"Dramatics","\u20B9 92,000"));
        list.add(new Events(R.drawable.ic_photography,R.color.fb,"Photography","\u20B9 10,000"));
        list.add(new Events(R.drawable.ic_literary,R.color.insta,"Literary","\u20B9 56,000"));
        list.add(new Events(R.drawable.ic_informals,R.color.red,"Informals"," "));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        profileName = findViewById(R.id.profile_name);
        profileName.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        profileName.setSelected(true);
        profileInstitute = findViewById(R.id.profile_institute);
        profileInstitute.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        profileInstitute.setSelected(true);
        profileEmail = findViewById(R.id.profile_culfest_email);
        profileId = findViewById(R.id.profile_culfest_id);
        profileTShirt = findViewById(R.id.t_shirt_size);
        profileImage = findViewById(R.id.profile_image);
        Picasso.get().load(firebaseUser.getPhotoUrl()).placeholder(R.drawable.ic_launcher).into(profileImage);
        profilePayment = findViewById(R.id.profile_payment);
        progress = findViewById(R.id.profile_progress_bar);
        eventRecycler = findViewById(R.id.event_chip_rv);

        fetchDetails(uid);
        makeQRCode(uid);

        findViewById(R.id.qr_code_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQRCode();
            }
        });

        findViewById(R.id.log_out_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
        eventRecycler.setLayoutManager(gridLayoutManager);
        EventChipAdapter adapter = new EventChipAdapter(ProfileActivity.this,list);
        eventRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        eventRecycler.hasFixedSize();
    }

    private void fetchDetails(String uid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progress.setVisibility(View.GONE);
                User user = dataSnapshot.getValue(User.class);
                profileName.setText(user.getName());
                profileEmail.setText(user.getEmail());
                profileId.setText(user.getCulfestID());
                profileInstitute.setText(user.getInstituteName());
                profileTShirt.setText(user.getTshirtSize());
                if (user.isPayment())
                    profilePayment.setImageResource(R.drawable.ic_tick);
                else
                    profilePayment.setImageResource(R.drawable.ic_cross);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void makeQRCode(String uid) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(uid, BarcodeFormat.QR_CODE, 480, 480);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showQRCode() {
        new Rect();
        Rect location;
        location = locateView(profileEmail);
        View popupView = getLayoutInflater().inflate(R.layout.qr_code_layout, null);
        qr = popupView.findViewById(R.id.qr_code_image);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(profileEmail, Gravity.TOP | Gravity.CENTER_HORIZONTAL, location.left, location.bottom);
        qr.setImageBitmap(bitmap);
    }

    private void logOut() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ProfileActivity.this);
        builder.setTitle("Log Out");
        StringBuilder sb = new StringBuilder();
        sb.append("Do you want to Log Out?");
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.web_client_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, gso);
                googleSignInClient.signOut().addOnCompleteListener(ProfileActivity.this,
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(ProfileActivity.this, SplashActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(0, 0);
                            }
                        });
            }
        });
        builder.setNeutralButton("No",null);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
