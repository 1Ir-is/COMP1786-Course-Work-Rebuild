package com.example.bottomnavigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bottomnavigation.databinding.FragmentAddBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    private FragmentAddBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Find the TextViews by their IDs
        TextView nameHikeLabel = view.findViewById(R.id.nameHike);
        TextView nameLocationLabel = view.findViewById(R.id.nameLocation);
        TextView dateHikeLabel = view.findViewById(R.id.dateHike);
        TextView parkingLabel = view.findViewById(R.id.textViewParking);
        TextView lengthHikeLabel = view.findViewById(R.id.lengthHike);
        TextView levelHikeLabel = view.findViewById(R.id.levelHike);

        // Create a red asterisk (*)
        String redAsterisk = " *";

        // Create a SpannableString with the label text and the red asterisk
        SpannableString nameHikeSpan = new SpannableString(nameHikeLabel.getText() + redAsterisk);
        SpannableString nameLocationSpan = new SpannableString(nameLocationLabel.getText() + redAsterisk);
        SpannableString dateHikeSpan = new SpannableString(dateHikeLabel.getText() + redAsterisk);
        SpannableString parkingSpan = new SpannableString(parkingLabel.getText() + redAsterisk);
        SpannableString lengthHikeSpan = new SpannableString(lengthHikeLabel.getText() + redAsterisk);
        SpannableString levelHikeSpan = new SpannableString(levelHikeLabel.getText() + redAsterisk);

        // Set the text color of the red asterisk to red
        int redColor = Color.RED;
        nameHikeSpan.setSpan(new ForegroundColorSpan(redColor), nameHikeSpan.length() - 1, nameHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        nameLocationSpan.setSpan(new ForegroundColorSpan(redColor), nameLocationSpan.length() - 1, nameLocationSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dateHikeSpan.setSpan(new ForegroundColorSpan(redColor), dateHikeSpan.length() - 1, dateHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        parkingSpan.setSpan(new ForegroundColorSpan(redColor), parkingSpan.length() - 1, parkingSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        lengthHikeSpan.setSpan(new ForegroundColorSpan(redColor), lengthHikeSpan.length() - 1, lengthHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        levelHikeSpan.setSpan(new ForegroundColorSpan(redColor), levelHikeSpan.length() - 1, levelHikeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableStrings as the text for the TextViews
        nameHikeLabel.setText(nameHikeSpan);
        nameLocationLabel.setText(nameLocationSpan);
        dateHikeLabel.setText(dateHikeSpan);
        parkingLabel.setText(parkingSpan);
        lengthHikeLabel.setText(lengthHikeSpan);
        levelHikeLabel.setText(levelHikeSpan);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user-input data
                String name = ((EditText) view.findViewById(R.id.nameHikeText)).getText().toString();
                String location = ((EditText) view.findViewById(R.id.locationText)).getText().toString();
                String date = ((EditText) view.findViewById(R.id.dateHikeText)).getText().toString();
                String parkingAvailable = ((RadioButton) view.findViewById(R.id.radioButtonYes)).isChecked() ? "Yes" : "No";
                String lengthHike = ((EditText) view.findViewById(R.id.lengthHikeText)).getText().toString();
                String difficultyLevel = ((Spinner) view.findViewById(R.id.levelHikeSpinner)).getSelectedItem().toString();
                String description = ((EditText) view.findViewById(R.id.descriptionHikeText)).getText().toString();

                // Create an intent to start the ConfirmationActivity
                Intent intent = new Intent(getActivity(), ConfirmationActivity.class);

                // Pass user-input data as extras to the intent
                intent.putExtra("name", name);
                intent.putExtra("location", location);
                intent.putExtra("date", date);
                intent.putExtra("parkingAvailable", parkingAvailable);
                intent.putExtra("lengthHike", lengthHike);
                intent.putExtra("difficultyLevel", difficultyLevel);
                intent.putExtra("description", description);

                // Start the ConfirmationActivity
                startActivity(intent);
            }
        });

        return view;
    }
}