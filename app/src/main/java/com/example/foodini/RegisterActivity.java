package com.example.foodini;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        final EditText inputEmail = (EditText) findViewById(R.id.input_email);
        final EditText inputName = (EditText) findViewById(R.id.input_nickname);
        final EditText inputPassword = (EditText) findViewById(R.id.input_password);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final TextView wrongEmailTextView = (TextView) findViewById(R.id.wrongEmailTextView);
        final TextView wrongNameTextView = (TextView) findViewById(R.id.wrongNameTextView);
        final TextView wrongPasswordTextView = (TextView) findViewById(R.id.wrongPasswordTextView);

        welcomeTextView.setText("");

        mAuth = FirebaseAuth.getInstance();

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ifEmailCorrect(inputEmail.getText().toString())) {
                    wrongEmailTextView.setVisibility(TextView.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ifEmailCorrect(inputEmail.getText().toString())) {
                    wrongEmailTextView.setVisibility(TextView.INVISIBLE);
                } else {
                    if (!inputEmail.getText().toString().equals("")) {
                        wrongEmailTextView.setVisibility(TextView.VISIBLE);
                    } else {
                        if (!inputEmail.hasFocus()) {
                            inputEmail.setHintTextColor(Color.parseColor("#F42222"));
                            inputEmail.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F42222")));
                        }
                        else {
                            inputEmail.setHintTextColor(Color.parseColor("#808080"));
                            inputEmail.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                        }
                    }
                }

            }
        });


        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ifLoginNameCorrect(inputName.getText().toString())) {
                    wrongNameTextView.setVisibility(TextView.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ifLoginNameCorrect(inputName.getText().toString())) {
                    wrongNameTextView.setVisibility(TextView.INVISIBLE);
                } else {
                    if (!inputName.getText().toString().equals("")) {
                        wrongNameTextView.setVisibility(TextView.VISIBLE);
                    } else {
                        if (!inputName.hasFocus()) {
                            inputName.setHintTextColor(Color.parseColor("#F42222"));
                            inputName.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F42222")));
                        }
                        else {
                            inputName.setHintTextColor(Color.parseColor("#808080"));
                            inputName.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                        }
                    }
                }

            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ifPasswordCorrect(inputPassword.getText().toString())) {
                    wrongPasswordTextView.setVisibility(TextView.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ifPasswordCorrect(inputPassword.getText().toString())) {
                    wrongPasswordTextView.setVisibility(TextView.INVISIBLE);
                } else {
                    if (!inputPassword.getText().toString().equals("")) {
                        wrongPasswordTextView.setVisibility(TextView.VISIBLE);
                    } else {
                        if (!inputPassword.hasFocus()) {
                            inputPassword.setHintTextColor(Color.parseColor("#F42222"));
                            inputPassword.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F42222")));
                        }
                        else {
                            inputPassword.setHintTextColor(Color.parseColor("#808080"));
                            inputPassword.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
                        }
                    }
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String name = inputName.getText().toString();
                String password = inputPassword.getText().toString();

                boolean registrationPossible = ifEmailCorrect(email)
                        && ifLoginNameCorrect(name)
                        && ifPasswordCorrect(password);

                if(registrationPossible) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Log.d("CreateNewUser", "createUserWithEmail:success");
                                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, IntroActivity.class);
                                startActivity(intent);
                            } else {
                                task.getException();
                                Log.d("CreateNewUser", "createUserWithEmail:failure");
                                Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean ifLoginNameCorrect(String loginName) {
        if (loginName.length() <= 2 || loginName.length() >= 16) return false;
        return true;
    }

    private boolean ifPasswordCorrect(String password) {
        if (password.length() < 8) return false;
        return true;
    }

    private boolean ifEmailCorrect(String email) {
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

}
