package com.example.mughees.sqliteassignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText oldemail,oldpassword,newemail,newpassword;
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        oldemail = findViewById(R.id.oldemail);
        oldpassword = findViewById(R.id.oldpassword);
        newemail = findViewById(R.id.newemail);
        newpassword = findViewById(R.id.newpassword);
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
    }

    private void updatedata() {
        String oemail, opass, nemail, npass;
        oemail = oldemail.getText().toString();
        opass = oldpassword.getText().toString();
        nemail = newemail.getText().toString();
        npass = newpassword.getText().toString();
        if (oemail.equals("")||opass.equals("")||nemail.equals("")||npass.equals("")){
            Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else if (!isEmailValid(oemail)){
            oldemail.setError("Old Email is Invalid ");
            oldemail.requestFocus();
            return;

        }
        else if (!isEmailValid(nemail)){
            newemail.setError("New Email is invalid");
            newemail.requestFocus();
            return;
        }
        else {

            myDB mydb = new myDB(this);
            Boolean deletemail = mydb.deleteuser(oemail);
            if (deletemail == true){
                Boolean isupdate = mydb.insert(nemail,npass);
                if (isupdate==true){

                    Intent i = new Intent(getApplicationContext(),profile.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(),"Value updated",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email not matched",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Your old email is not valid",Toast.LENGTH_SHORT).show();
            }
        }
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
