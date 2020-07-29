package com.example.logind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email,pass;
    private TextView info;
    private Button btn1,btn2;
    FirebaseAuth FB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        info=(TextView)findViewById(R.id.nfo);
        btn1=(Button)findViewById(R.id.resign);
        btn2=(Button)findViewById(R.id.logins);
        FB=FirebaseAuth.getInstance();

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usermail=email.getText().toString();
                String userpass=pass.getText().toString();

                if (usermail.isEmpty())
                {
                    email.setError("Please Provide username");
                    email.requestFocus();
                }
                if(userpass.isEmpty())
                {
                    pass.setError("Please Provide Password");
                    pass.requestFocus();
                }
                else if(usermail.isEmpty() && userpass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"THE Fields Are Empty Please Check",Toast.LENGTH_SHORT).show();

                }
                else if(!(usermail.isEmpty() && userpass.isEmpty()))
                {
                    FB.createUserWithEmailAndPassword(usermail,userpass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful,Try Again Later",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity( new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }
            }

        });
       btn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               startActivity( new Intent(MainActivity.this,LoginActivity.class));
           }
       });
    }
}
