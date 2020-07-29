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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText lgemail,lgpass;
    private TextView lginfo;
    private Button b1,b2;
    FirebaseAuth FB;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lgemail=(EditText) findViewById(R.id.mail);
        lgpass=(EditText)findViewById(R.id.pass);
        lginfo=(TextView)findViewById(R.id.nfo);
        b2=(Button)findViewById(R.id.resign);
        b1=(Button)findViewById(R.id.logins);
        FB= FirebaseAuth.getInstance();
        mAuthStateListener=new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {        FirebaseUser mfbuser = FB.getCurrentUser();
                      if(mfbuser!=null)
                      {
                          Toast.makeText(LoginActivity.this,"You Have Logged in",Toast.LENGTH_SHORT).show();
                          Intent i= new Intent(LoginActivity.this,HomeActivity.class);
                          startActivity(i);
                      }
                      else
                      {
                          Toast.makeText(LoginActivity.this,"plz log in",Toast.LENGTH_SHORT).show();
                      }

            }
        };
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usere=lgemail.getText().toString();
                String userpa=lgpass.getText().toString();

                if (usere.isEmpty())
                {
                    lgemail.setError("Please Provide username");
                    lgemail.requestFocus();
                }
                if(userpa.isEmpty())
                {
                    lgpass.setError("Please Provide Password");
                    lgpass.requestFocus();
                }
                else if(usere.isEmpty() && userpa.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"THE Fields Are Empty Please Check",Toast.LENGTH_SHORT).show();

                }
                else if(!(usere.isEmpty() && userpa.isEmpty()))
                {
                    FB.signInWithEmailAndPassword(usere,userpa).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"Login not successful",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intohome=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intohome);

                            }
                        }
                    });
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity( new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}
