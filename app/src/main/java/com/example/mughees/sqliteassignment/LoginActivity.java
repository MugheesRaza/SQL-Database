package com.example.mughees.sqliteassignment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText useremail ,password;
    Button login_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useremail = findViewById(R.id.loginusername);
        password = findViewById(R.id.loginpassword);
        login_user = findViewById(R.id.userlogin);
        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeSQL();
            }
        });
    }

    private void executeSQL() {
        myDB mydb = new myDB(this);
       String email = useremail.getText().toString().trim();
       String upassword  = password.getText().toString().trim();
       Boolean checklogin = mydb.loginemailpassword(email,upassword);
       if (!isEmailValid(email)){
           useremail.setError("Invalid email");
           useremail.requestFocus();
           return;
       }
       else if (checklogin == true){
           Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
           Intent i = new Intent(getApplicationContext(),profile.class);
           startActivity(i);
           finish();
       }
       else {
           Toast.makeText(getApplicationContext(),"Login failed ",Toast.LENGTH_SHORT).show();
       }

    }
    boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
}
