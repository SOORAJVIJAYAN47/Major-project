package com.android.pharmaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class ProfileUpdateActivity extends AppCompatActivity {
    private EditText previousIllnessesInput;
    private EditText surgeriesInput;
    private EditText medicationsInput;
    private EditText dietInput;
    private EditText exerciseHabitsInput;
    private EditText alcoholUseInput;
    private EditText tobaccoUseInput;
    private EditText allergyTypeInput;
    private EditText allergySymptomsInput;
    private Spinner allergySeveritySpinner;
    private Button submitButton;
    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREFS_FILE = "my_shared_prefs";
    private static final String KEY_PREV_ILLNESSES = "previous_illnesses";
    private static final String KEY_SURGERIES = "surgeries";
    private static final String KEY_MEDICATIONS = "medications";
    private static final String KEY_DIET = "diet";
    private static final String KEY_EXERCISE_HABITS = "exercise_habits";
    private static final String KEY_ALCOHOL_USE = "alcohol_use";
    private static final String KEY_TOBACCO_USE = "tobacco_use";
    private static final String KEY_ALLERGY_TYPE = "allergy_type";
    private static final String KEY_ALLERGY_SYMPTOMS = "allergy_symptoms";
    private static final String KEY_ALLERGY_SEVERITY = "allergy_severity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        previousIllnessesInput = findViewById(R.id.previous_illnesses_input);
        surgeriesInput = findViewById(R.id.surgeries_input);
        medicationsInput = findViewById(R.id.medications_input);
        dietInput = findViewById(R.id.diet_input);
        exerciseHabitsInput = findViewById(R.id.exercise_habits_input);
        alcoholUseInput = findViewById(R.id.alcohol_use_input);
        tobaccoUseInput = findViewById(R.id.tobacco_use_input);
        allergyTypeInput = findViewById(R.id.allergy_type_input);
        allergySymptomsInput = findViewById(R.id.allergy_symptoms_input);
        allergySeveritySpinner = findViewById(R.id.allergy_severity_spinner);
        submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String prevIllnesses = previousIllnessesInput.getText().toString().trim();
        String surgeries = surgeriesInput.getText().toString().trim();
        String medications = medicationsInput.getText().toString().trim();
        String diet = dietInput.getText().toString().trim();
        String exerciseHabits = exerciseHabitsInput.getText().toString().trim();
        String alcoholUse = alcoholUseInput.getText().toString().trim();
        String tobaccoUse = tobaccoUseInput.getText().toString().trim();
        String allergyType = allergyTypeInput.getText().toString().trim();
        String allergySymptoms = allergySymptomsInput.getText().toString().trim();
        String allergySeverity = allergySeveritySpinner.getSelectedItem().toString();

        if (prevIllnesses.isEmpty() || surgeries.isEmpty() || medications.isEmpty() ||
                diet.isEmpty() || exerciseHabits.isEmpty() || alcoholUse.isEmpty() ||
                tobaccoUse.isEmpty() || allergyType.isEmpty() || allergySymptoms.isEmpty() || allergySeverity.isEmpty())
        {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
        } else {

        }
    }
}
