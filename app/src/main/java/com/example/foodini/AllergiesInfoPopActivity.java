package com.example.foodini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AllergiesInfoPopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies_info_pop);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.6));

        TextView infoTextView = (TextView) findViewById(R.id.infoTextView);
        infoTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
