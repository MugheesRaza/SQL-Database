package com.example.mughees.sqliteassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText email ,password  ,confirmpassword;
    Button registeruser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.registername);
        password = findViewById(R.id.userregname);
        confirmpassword = findViewById(R.id.registerapassword);
        registeruser = findViewById(R.id.registeruser);
        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeSQlDatabase();
            }
        });

    }

    private void executeSQlDatabase() {
        String useremail,userpassword,userconfrimpassword;
        useremail = email.getText().toString();
        userpassword = password.getText().toString();
        userconfrimpassword = confirmpassword.getText().toString();
        if (useremail.equals("")||userpassword.equals("")||userconfrimpassword.equals("")){
            Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
        }
        else if (!isEmailValid(useremail)){
            email.setError("Invalid Email");
            email.requestFocus();
            return;
        }
        else {
            if (userpassword.equals(userconfrimpassword)){
                myDB mydb = new myDB(this);

                Boolean checkemail = mydb.checkmail(useremail);
                if (checkemail==true){
                    Boolean insert = mydb.insert(useremail,userpassword);
                    if (insert == true){
                        Intent i = new Intent(getApplicationContext(),profile.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email Already exists",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Password not match",Toast.LENGTH_SHORT).show();
            }
        }
    }
    boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
}
