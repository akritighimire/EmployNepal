package com.example.employnepal;

import android.os.Bundle;
//import android.provider.ContactsContract;
//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ListView;
//import android.os.Vibrator;
//import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class ViewDatabase extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference reference;
    private  String userID;
    private ListView mListView ;
    ArrayAdapter <UserInformation> adapter;
    //private EditText jobtitle, jobdescription, noofemployees, joblocation,jobcategory, workinghours, salary, applybefore ;
    //private ProgressBar progressBar;
    //private Button btnapply;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyy);

        mListView = (ListView) findViewById(R.id.listview);
        //btnapply= findViewById(R.id.apply);
        //btnback = findViewById(R.id.back_button);
        //progressBar = findViewById(R.id.progressbar);

        
        /* jobtitle = findViewById(R.id.job_title);
        jobdescription = findViewById(R.id.job_description);
        noofemployees = findViewById(R.id.no_of_employees);
        joblocation = findViewById(R.id.job_location);
        jobcategory = findViewById(R.id.job_category);
        noofemployees =  findViewById(R.id.no_of_employees);
        workinghours =  findViewById(R.id.working_hours);
        applybefore =  findViewById(R.id.apply_before);
        salary = findViewById(R.id.salary); */
        
        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference=database.getReference("UserInformation");//maile suru ma userinfo haleko thyina
        FirebaseUser user = auth.getCurrentUser();
        String userID = user.getUid();
        //userID = user.getUid();

        //ValueEventListener listener = new ValueEventListener()
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(snapshot); // comment thyo
                UserInformation post = snapshot.getValue(UserInformation.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }

            });
                //reference.addValueEventListener(listener);

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
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}