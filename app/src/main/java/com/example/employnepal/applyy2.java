package com.example.employnepal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class applyy2 extends AppCompatActivity {

    private Button btnapply;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyy);

        Button btnapplyy = (Button)findViewById(R.id.apply_exx);
        reference = FirebaseDatabase.getInstance().getReference();

        btnapplyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child("Name").setValue("Rita Pun");
                reference.child("Phone Number").setValue("9876543210");

            }
        });
    }



}
