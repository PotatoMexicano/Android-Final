package com.example.gabri.afinal;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    Control control;
    EditText txtName,txtLogin,txtPassword,txtPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtName = (EditText) findViewById(R.id.editText1);
        txtLogin = (EditText) findViewById(R.id.editText2);
        txtPassword = (EditText) findViewById(R.id.editText3);
        txtPassword2 = (EditText) findViewById(R.id.editText4);

        control =  new Control(this);

    }

    public void inscribe(View view){
        if(!(txtPassword.getText().toString().equals(""))) {
            if (txtPassword.getText().toString().equals(txtPassword2.getText().toString())) {
                if (!(txtName.getText().toString().equals("") && txtLogin.getText().toString().equals(""))) {
                    long x = control.insertUser(txtName.getText().toString(), txtLogin.getText().toString(), txtPassword.getText().toString());
                    Toast.makeText(this, String.valueOf(x), Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent main = new Intent(signup.this, MainActivity.class);
                            startActivity(main);
                        }
                    },2000);

                }
            }
        }
    }


}
