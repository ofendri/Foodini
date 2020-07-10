package com.example.foodini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcomeActivity();
            }
        });

        final AlphaAnimation textViewClick = new AlphaAnimation(1F, 0.8F);

        registerTextView = (TextView) findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(textViewClick);
                //registerTextView.setTextColor(Color.parseColor("#020024"));
                openRegisterActivity();
            }
        });
    }

    public void openWelcomeActivity() {
        Intent intent = new Intent(this, IntroActivity.class); //Original
        //Intent intent = new Intent(this, RecipesGeneratorActivity.class); //Debug
        startActivity(intent);
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class); //Debug
        startActivity(intent);
    }
}
