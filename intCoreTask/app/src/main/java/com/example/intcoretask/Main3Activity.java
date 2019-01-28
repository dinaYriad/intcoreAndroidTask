package com.example.intcoretask;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        show_profile();
    }

    public void show_profile()
    {
        User obj = new User();
        TextView tv = (TextView) findViewById(R.id.textView7);
        TextView tv1 = (TextView) findViewById(R.id.textView6);
        TextView tv2 = (TextView) findViewById(R.id.textView5);
        obj.getProfile();

        tv.setText(obj.get_name());
        tv1.setText(obj.get_email());
        tv2.setText(obj.get_phoneNumber());
    }
}
