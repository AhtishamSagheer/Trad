package com.ahtisham.sagheer.trading2.Authantication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.ahtisham.sagheer.trading2.Authantication.Model.MemberData;
import com.ahtisham.sagheer.trading2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    String uid, FirstName, LastName, Address, City, State, ZipCode, ApartmentNumber, Email, Password;

    FirebaseUser current_User_ID;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @BindView(R.id.firstName)
    EditText firstName;
    @BindView(R.id.lastName)
    EditText lastName;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.state)
    EditText state;
    @BindView(R.id.zipCode)
    EditText zipCode;
    @BindView(R.id.apartmentNumber)
    EditText apartmentNumber;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.confirmEmail)
    EditText confirmEmail;

    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.termAndCondition)
    CheckBox termAndCondition;
    @BindView(R.id.privacyCheck)
    CheckBox privacyCheck;
    @BindView(R.id.submit)
    Button submit;

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        termAndCondition = findViewById(R.id.termAndCondition);
        termAndCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Intent n = new Intent(RegisterActivity.this, TermAndConditionActivity.class);
                    startActivity(n);
                }
            }
        });
        privacyCheck = findViewById(R.id.privacyCheck);
        privacyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Intent n = new Intent(RegisterActivity.this, PervicyPolicyActivity.class);
                    startActivity(n);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TermAndConditionActivity.termAndCondition == 1)
                {
                    if (PervicyPolicyActivity.privacy == 1) {
                        FirstName = firstName.getText().toString();
                        LastName = lastName.getText().toString();
                        Address = address.getText().toString();
                        City = city.getText().toString();
                        State = state.getText().toString();
                        ZipCode = zipCode.getText().toString();
                        ApartmentNumber = apartmentNumber.getText().toString();
                        Email = email.getText().toString();
                        Password = password.getText().toString();

                        if (TextUtils.isEmpty(FirstName) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                            Toast.makeText(RegisterActivity.this, "Please Enter Email or Password", Toast.LENGTH_SHORT).show();
                        } else {
                            if (password.length() < 1) {
                                Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.setTitle("New Account");
                                progressDialog.setMessage("Please Wait");
                                progressDialog.show();
                                mAuth.createUserWithEmailAndPassword(Email, Password)
                                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    AddData();
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                }
                                            }
                                        });
                            }
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please accept Privacy Policy", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please accept our Term & Condition", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AddData() {
        MemberData memberData = new MemberData(FirstName, LastName, Address, City, State, ZipCode, ApartmentNumber, Email);
        uid = mAuth.getCurrentUser().getUid();
        myRef.child("Users").child(uid).setValue(memberData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

}
