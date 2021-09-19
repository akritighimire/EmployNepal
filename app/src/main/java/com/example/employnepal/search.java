package com.example.employnepal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class search extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Button button =(Button)findViewById(R.id.searchdemo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(search.this,applyy.class);
                //Intent intent1=(new Intent(search.this ,apply_job.class));
                startActivity(intent);
                //startActivity(intent1);
            }
        });
    }
}

