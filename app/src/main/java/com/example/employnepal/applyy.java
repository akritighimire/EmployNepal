package com.example.employnepal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class applyy extends AppCompatActivity {

    public static final String TAG = "ViewDatabase";
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ListView listView;
    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter <UserInformation> adapter;
    String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyy);
        listView=(ListView)findViewById(R.id.list_view);
        final ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listView.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseAuth.getInstance().getCurrentUser().getUid();//added later
//        String userID = UserInfo.getUid();
        ref =database.getReference("UserInformation");
        ref= FirebaseDatabase.getInstance().getReference();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                list.add(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(snapshot); // comment thyo
                UserInformation post = snapshot.getValue(UserInformation.class);
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                list.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserInformation uInfo = new UserInformation();
            uInfo.setJobtitle(ds.child(userID).getValue(UserInformation.class).getJobtitle());
            uInfo.setJobdescription(ds.child(userID).getValue(UserInformation.class).getJobdescription());
            uInfo.setNoofemployees(ds.child(userID).getValue(UserInformation.class).getNoofemployees());
            uInfo.setJoblocation(ds.child(userID).getValue(UserInformation.class).getJoblocation());
            uInfo.setJobcategory(ds.child(userID).getValue(UserInformation.class).getJobcategory());
            uInfo.setWorkinghrs(ds.child(userID).getValue(UserInformation.class).getWorkinghrs());
            uInfo.setSalary(ds.child(userID).getValue(UserInformation.class).getSalary());
            uInfo.setApplybefore(ds.child(userID).getValue(UserInformation.class).getApplybefore());
            //display all the information
            Log.d(TAG, "showData: jobtitle: " + uInfo.getJobtitle());
            Log.d(TAG, "showData: jobdescription: " + uInfo.getJobdescription());
            Log.d(TAG, "showData: noofemployees: " + uInfo.getNoofemployees());
            Log.d(TAG, "showData: joblocation: " + uInfo.getJoblocation());
            Log.d(TAG, "showData: jobcategory: " + uInfo.getJobcategory());
            Log.d(TAG, "showData: workinghrs: " + uInfo.getWorkinghrs());
            Log.d(TAG, "showData: salary: " + uInfo.getSalary());
            Log.d(TAG, "showData: applybefore: " + uInfo.getApplybefore());

            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getJobtitle());
            array.add(uInfo.getJobdescription());
            array.add(uInfo.getNoofemployees());
            array.add(uInfo.getJoblocation());
            array.add(uInfo.getJobcategory());
            array.add(uInfo.getWorkinghrs());
            array.add(uInfo.getSalary());
            array.add(uInfo.getApplybefore());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            listView.setAdapter(adapter);
        }
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}