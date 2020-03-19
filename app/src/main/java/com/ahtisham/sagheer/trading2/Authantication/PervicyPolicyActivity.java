package com.ahtisham.sagheer.trading2.Authantication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahtisham.sagheer.trading2.R;

import androidx.appcompat.app.AppCompatActivity;

public class PervicyPolicyActivity extends AppCompatActivity {

    public static int privacy=0;
    Button privacyAccept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pervicy_policy);

        privacyAccept=findViewById(R.id.privacyAccept);
        privacyAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy=1;
            }
        });

    }
}
