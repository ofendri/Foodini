package com.example.foodini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ChooseIngredients extends AppCompatActivity {

    int[] IMAGES = {R.drawable.ic_tomato, R.drawable.ic_pepper, R.drawable.ic_meat, R.drawable.ic_sausage, R.drawable.ic_mushrooms, R.drawable.ic_onion};

    String[] namesOfIngredients = {"Tomato", "Pepper", "Meat", "Sausage", "Mushroom", "Onion"};

    SeekBar ratingSeekBar;
    TextView ratingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ingredients);

        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        Button nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcomeActivity();
            }
        });

    }

    public void openWelcomeActivity() {
        Intent intent = new Intent(this, RecipesGeneratorActivity.class);
        startActivity(intent);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_layout_bis, null);

            ImageView ingredientImageView = (ImageView) view.findViewById(R.id.ingredientImageView);
            TextView ingredientNameTextView = (TextView) view.findViewById(R.id.ingredientNameTextView);
            SeekBar ratingSeekBar = (SeekBar) view.findViewById(R.id.ratingSeekBar);
            final TextView ratingTextView = (TextView) view.findViewById(R.id.ratingTextView);


            ingredientImageView.setImageResource(IMAGES[i]);
            ingredientNameTextView.setText(namesOfIngredients[i]);

            ratingTextView.setTextColor(Color.parseColor("#FF1744"));


            ratingSeekBar.setMax(4);
            ratingSeekBar.setProgress(0);

            ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    ratingTextView.setText(String.valueOf(progress + 1) + ".0");
                    switch(progress) {
                        case 0:
                            ratingTextView.setTextColor(Color.parseColor("#FF4558"));
                            break;
                        case 1:
                            ratingTextView.setTextColor(Color.parseColor("#F5771F"));
                            break;
                        case 2:
                            ratingTextView.setTextColor(Color.parseColor("#D1A400"));
                            break;
                        case 3:
                            ratingTextView.setTextColor(Color.parseColor("#97C91C"));
                            break;
                        case 4:
                            ratingTextView.setTextColor(Color.parseColor("#00E676"));
                            break;
                        default:
                            ratingTextView.setTextColor(Color.parseColor("#D1A400"));
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            return view;
        }
        
        

    }
}
