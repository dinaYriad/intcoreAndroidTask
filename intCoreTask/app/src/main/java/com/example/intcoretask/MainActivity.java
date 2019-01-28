package com.example.intcoretask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn_Click(View v)
    {
        EditText ed = (EditText) findViewById(R.id.editText);
        EditText ed1 = (EditText) findViewById(R.id.editText2);
        User obj = new User();
        obj.set_name(ed.getText().toString());
        obj.set_password(ed1.getText().toString());
        String message = obj.signIn();


        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        if(message.length()!=0) //Correct password and username.
        {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);

        }


    }

    public void goToSignUp_Click(View v)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


}
