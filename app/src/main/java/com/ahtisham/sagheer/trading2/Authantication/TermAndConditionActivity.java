package com.ahtisham.sagheer.trading2.Authantication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahtisham.sagheer.trading2.R;

public class TermAndConditionActivity extends AppCompatActivity {

    Button termAndConditionButton;
    public static int termAndCondition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_condition);

        termAndConditionButton=findViewById(R.id.termAndCondition);
        termAndConditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                termAndCondition=1;
            }
        });
    }
}
