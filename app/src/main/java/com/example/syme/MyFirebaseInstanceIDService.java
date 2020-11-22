package com.example.syme;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private FirebaseAuth mAuthh;
    private DatabaseReference mDataBasee;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String s = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        String idee = mAuthh.getCurrentUser().getUid();
        /*mAuthh = FirebaseAuth.getInstance();
        mDataBasee = FirebaseDatabase.getInstance().getReference();
        mDataBasee.child("users").child(idee).child("Token").setValue(s);*/
    }
}
