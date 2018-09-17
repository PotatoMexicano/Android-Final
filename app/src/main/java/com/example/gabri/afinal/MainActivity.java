package com.example.gabri.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView signup_TV;
    Control control;
    EditText txtLogin,txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new Control(this);

        txtLogin = (EditText) findViewById(R.id.editText2);
        txtPassword = (EditText) findViewById(R.id.editText3);

        signup_TV = (TextView) findViewById(R.id.textView);
        signup_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_Signup();
            }
        });
    }
    public void open_Signup(){
        Intent signup = new Intent(this, com.example.gabri.afinal.signup.class);
        startActivity(signup);
    } //Open singup Intent
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.menu_add){
            open_Signup();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public void selectUserLogin(View view){
       boolean login_resp =  control.selectUser(txtLogin.getText().toString(), txtPassword.getText().toString());
       if(login_resp == true){
           Intent event = new Intent(this, Event.class);
           startActivity(event);
       }else{
           Toast.makeText(this, "U wrong dude", Toast.LENGTH_SHORT).show();
       }
    } //Login
}
