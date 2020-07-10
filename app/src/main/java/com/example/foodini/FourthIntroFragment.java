package com.example.foodini;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FourthIntroFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fourth_fragment_intro, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Button nextButton = (Button) getView().findViewById(R.id.nextButton);
        final RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.radioGroup);
        final TextView noChoiceTextView = (TextView) getView().findViewById(R.id.noChoiceTextView);
        final LinearLayout moreInfoLayout = (LinearLayout) getView().findViewById(R.id.moreInfoLinearLayout);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    noChoiceTextView.setVisibility(TextView.VISIBLE);
                } else {
//                     noChoiceTextView.setVisibility(TextView.INVISIBLE);
//                     FragmentManager fm = getActivity().getSupportFragmentManager();
//                     ThirdIntroFragment fragment = new ThirdIntroFragment();
//                     fm.beginTransaction()
//                             .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
//                                     R.anim.enter_left_to_right, R.anim.exit_left_to_right)
//                             .replace(R.id.flFragment, fragment)
//                             .addToBackStack(null)
//                             .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                             .commit();
                    openIngredientsActivity();
                }
            }
        });

        moreInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllergiesInfoPopActivity.class));
            }
        });
    }

    public void openIngredientsActivity() {
        Intent intent = new Intent(getActivity(), ChooseIngredients.class);
        startActivity(intent);
    }

}
