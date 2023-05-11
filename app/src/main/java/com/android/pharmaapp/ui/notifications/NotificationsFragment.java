package com.android.pharmaapp.ui.notifications;

import static com.android.pharmaapp.AndroidUtil.getUserId;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.pharmaapp.LoginActivity;
import com.android.pharmaapp.R;
import com.android.pharmaapp.Retrofit.ApiClient;
import com.android.pharmaapp.Retrofit.ApiServiceInterface;
import com.android.pharmaapp.SignupActivity;
import com.android.pharmaapp.models.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment {
    EditText previousIllnessInput,
             surgeriesInput,
             medicationsInput,
             diet_input,
             exerciseHabitsInput,
             alcoholUseInput,
             tobaccoUseInput,
             allergyTypeInput,
             allergySymptomsInput,
             allergyTimeFrameInput,
             allergyHistoryInput,
             allergyDiagnosisInput,
             allergyTreatmentsInput,
             allergyManagementInput;
    Spinner allergySeveritySpinner;
    Button submitBtn;
    String severity="mild";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        previousIllnessInput = root.findViewById(R.id.previous_illnesses_input);
        surgeriesInput = root.findViewById(R.id.surgeries_input);
        medicationsInput = root.findViewById(R.id.medications_input);
        diet_input = root.findViewById(R.id.diet_input);
        exerciseHabitsInput = root.findViewById(R.id.exercise_habits_input);
        alcoholUseInput = root.findViewById(R.id.alcohol_use_input);
        tobaccoUseInput = root.findViewById(R.id.tobacco_use_input);
        allergyTypeInput = root.findViewById(R.id.allergy_type_input);
        allergySymptomsInput = root.findViewById(R.id.allergy_symptoms_input);
        allergyTimeFrameInput = root.findViewById(R.id.allergy_onset_timeframe_input);
        allergyHistoryInput = root.findViewById(R.id.allergy_history_input);
        allergyDiagnosisInput = root.findViewById(R.id.allergy_diagnosis_input);
        allergyTreatmentsInput = root.findViewById(R.id.allergy_treatments_input);
        allergyManagementInput = root.findViewById(R.id.allergy_management_input);
        allergySeveritySpinner=root.findViewById(R.id.allergy_severity_spinner);
        submitBtn=root.findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                severity=allergySeveritySpinner.getSelectedItem().toString();
               // Toast.makeText(getContext(), allergySeveritySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if(validateFields())
                {
                    ApiServiceInterface api = ApiClient.getClient().create(ApiServiceInterface.class);
                    api.updateProfile(String.valueOf(getUserId(getContext())),
                            previousIllnessInput.getText().toString(),
                            surgeriesInput.getText().toString(),
                            medicationsInput.getText().toString(),
                            diet_input.getText().toString(),
                            exerciseHabitsInput.getText().toString(),
                            alcoholUseInput.getText().toString(),
                            tobaccoUseInput.getText().toString(),
                            allergyTypeInput.getText().toString(),
                            allergySymptomsInput.getText().toString(),
                            severity,
                            allergyTimeFrameInput.getText().toString(),
                            allergyHistoryInput.getText().toString(),
                            allergyDiagnosisInput.getText().toString(),allergyTreatmentsInput.getText().toString(),
                            allergyManagementInput.getText().toString()
                            ).enqueue(new Callback<Root>() {
                        @Override
                        public void onResponse(Call<Root> call, Response<Root> response) {
                            Root root = response.body();
                            if (root.status) {
                                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                               // startActivity(new Intent(getContext(), LoginActivity.class));
                            } else {
                                Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Root> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    private boolean validateFields() {
        boolean isValid = true;

        // Check if all required fields are filled
        if (previousIllnessInput.getText().toString().isEmpty()) {
            previousIllnessInput.setError("Previous illness is required");
            isValid = false;
        }
        if (surgeriesInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (medicationsInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (diet_input.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (exerciseHabitsInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (alcoholUseInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (tobaccoUseInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyTypeInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergySymptomsInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyTimeFrameInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyHistoryInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyDiagnosisInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyTreatmentsInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }
        if (allergyManagementInput.getText().toString().isEmpty()) {
            allergyTypeInput.setError("Allergy type is required");
            isValid = false;
        }


        return isValid;
    }

}