package com.example.foodini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class RecipesGeneratorActivity extends AppCompatActivity {

    ArrayList<String> numberList = new ArrayList<>();

    TextView recipeTitleTextView;
    ImageView recipeImageView;
    TextView recipeInstructionsTextView;
    TextView recipeIngredientsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_generator);

        recipeTitleTextView = (TextView) findViewById(R.id.recipeTitleTextView);
        recipeImageView = (ImageView) findViewById(R.id.recipeImageView);
        recipeInstructionsTextView = (TextView) findViewById(R.id.recipeInstructionsTextView);
        recipeIngredientsTextView = (TextView) findViewById(R.id.recipeIngredientsTextView);

        get_json();
    }

    public void get_json () {
        String json;

        try {
            InputStream is = getAssets().open("RecipeDB.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                if (obj.getString("strMeal").equals("Lasagne")) {
                    recipeTitleTextView.setText(obj.getString("strMeal"));
                    recipeInstructionsTextView.setText(obj.getString("strInstructions"));
                    recipeIngredientsTextView.setText(stringifyArray(getAllIngredientsJSON(obj)));
                    String url = obj.getString("strMealThumb");
                    loadImageFromURL(url);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadImageFromURL(String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_loading)
        .error(R.drawable.ic_error_loading_image)
        .into(recipeImageView);
    }

    public String[] getAllIngredientsJSON(JSONObject obj) {
        final String[] ingredients = new String[19];
        try {
            for (int i = 0; i < ingredients.length; i++) {
                ingredients[i] = obj.getString("strIngredient" + ((i + 1) + ""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public String stringifyArray(String[] s) {
        String str = "";
        for (int i = 0; i < s.length; i++) {
            str += s[i] + ", ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
}
