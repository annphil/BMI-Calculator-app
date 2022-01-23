package com.example.bmicalcalator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setupButtonListener();
    }

    private void findView(){
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18)
                    displayResult(bmiResult);
                else
                    displayGuidance(bmiResult);
            }
        });
    }

    private double calculateBmi() {
        String ageText = ageEditText.getText().toString();
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        //Converting the number 'String' into 'int' variable
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet*12) + inches;

        double heightInMeters = totalInches*0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        // Converting decimal/double value to String for TextView
        // Deciding number of variables to print
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullStringResult;

        if(bmi < 18.5)
            fullStringResult = bmiTextResult + " - You are underweight";
        else if (bmi > 25)
            fullStringResult = bmiTextResult + " - You are overweight";
        else
            fullStringResult = bmiTextResult + " - You are a healthy weight";

        resultText.setText(fullStringResult);

        }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullStringResult;

        if(maleRadioButton.isChecked())
            fullStringResult = bmiTextResult + " - As you are under 18, please consult a doctor to know healthy BMI range for a boy. ";
        else if (femaleRadioButton.isChecked())
            fullStringResult = bmiTextResult + " - As you are under 18, please consult a doctor to know the healthy BMI range for a girl. ";
        else
            fullStringResult = bmiTextResult + " - As you are under 18, please consult a doctor to know the healthy BMI range.";

        resultText.setText(fullStringResult);
    }
}

