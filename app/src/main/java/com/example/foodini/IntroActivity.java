package com.example.foodini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class IntroActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        Fragment welcomeIntroFragment = new WelcomeIntroFragment();
        Fragment firstIntroFragment = new FirstIntroFragment();
        Fragment secondIntroFragment = new SecondIntroFragment();

        FrameLayout fl = findViewById(R.id.flFragment);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.flFragment, welcomeIntroFragment);
        ft.commit();

        //ft.replace(R.id.flFragment, firstIntroFragment);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openIngredientsActivity();
            }
        });
    }

    public void openIngredientsActivity() {
        Intent intent = new Intent(this, ChooseIngredients.class);
        startActivity(intent);
    }


}
