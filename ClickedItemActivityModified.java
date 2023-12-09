package com.zybooks.projecttwohainguyenui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ClickedItemActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    EditText editText;
    Spinner textSizeSpinner; // Added Spinner for text size selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_item);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.tvName);
        editText = findViewById(R.id.inputText);
        textSizeSpinner = findViewById(R.id.textSizeSpinner); // Initialize spinner from layout

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            String selectedName = intent.getStringExtra("name");
            int selectedImage = intent.getIntExtra("image", 0);

            textView.setText(selectedName);
            imageView.setImageResource(selectedImage);

            // Enhancement 1: Allowing users to add comments or reviews
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        String userComment = editText.getText().toString();
                        // Save the comment to data structure or send it to a server
                    }
                }
            });

            // Enhancement 2: Adding animations like fade in to the ImageView
            imageView.animate().alpha(1.0f).setDuration(1000);

            // Enhancement 3: Allowing users to customize the displayed item's details
            // Provide options to change the text size based on user preferences.

            // Set up the spinner with predefined text size options
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.text_size_options,
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            textSizeSpinner.setAdapter(adapter);

            // Handle spinner item selection to change text size
            textSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    // Get the selected text size from the spinner
                    String selectedTextSize = adapterView.getItemAtPosition(position).toString();

                    // Set the text size based on the selected option
                    switch (selectedTextSize) {
                        case "Small":
                            textView.setTextSize(14);
                            break;
                        case "Medium":
                            textView.setTextSize(18);
                            break;
                        case "Large":
                            textView.setTextSize(24);
                            break;
                        // Add more options as needed
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // Do nothing if nothing is selected
                }
            });
        }
    }
}
