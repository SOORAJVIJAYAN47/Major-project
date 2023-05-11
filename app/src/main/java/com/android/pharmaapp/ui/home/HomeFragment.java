package com.android.pharmaapp.ui.home;

import static com.android.pharmaapp.AndroidUtil.getUserId;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.pharmaapp.HomeActivity;
import com.android.pharmaapp.LoginActivity;
import com.android.pharmaapp.adapters.ComponentAdapter;
import com.android.pharmaapp.R;
import com.android.pharmaapp.Retrofit.ApiClient;
import com.android.pharmaapp.Retrofit.ApiServiceInterface;
import com.android.pharmaapp.models.Component;
import com.android.pharmaapp.models.MedicinesDetail;
import com.android.pharmaapp.models.Root;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    EditText medicineEditText;
    RecyclerView componentRecyclerView;
    TextView brandNamesTv,
            brandHintTv,
            genericNameTv,
            genericHintTv,
            accessionNumberTv,
            accesssionHintTv,
            descriptionTv,
            descriptionHintTv,
            chemicalFormulaHintTv,
            chemicalFormulaTv,
            componentHintTv;
    Button submitButton;
    Button askDoctorButton;
  //  String[] paracetamolIngredients = {"Acetaminophen", "Lactose", "Starch", "Microcrystalline cellulose", "Polyvinylpyrrolidone", "Hydroxypropyl methylcellulose", "Magnesium stearate", "Croscarmellose sodium", "Sodium starch glycolate"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        medicineEditText = root.findViewById(R.id.medicine_edit_text);
        componentRecyclerView = root.findViewById(R.id.components_list);
        brandNamesTv = root.findViewById(R.id.brand_names_text);
        brandHintTv = root.findViewById(R.id.brand_names_hint);
        genericNameTv = root.findViewById(R.id.generic_name_text);
        genericHintTv = root.findViewById(R.id.generic_name_hint);
        accessionNumberTv = root.findViewById(R.id.accession_text);
        accesssionHintTv = root.findViewById(R.id.accession_hint);
        descriptionTv = root.findViewById(R.id.description_text);
        descriptionHintTv = root.findViewById(R.id.description_hint);
        chemicalFormulaTv = root.findViewById(R.id.chemical_formula_text);
        chemicalFormulaHintTv = root.findViewById(R.id.chemical_formula_hint);
        componentHintTv = root.findViewById(R.id.components_label);
        submitButton = root.findViewById(R.id.submit_button);
        askDoctorButton=root.findViewById(R.id.ask_doctor_button);
        hideViews();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!medicineEditText.getText().toString().isEmpty()) {

                    getMedicineDetails(medicineEditText.getText().toString().toLowerCase().trim());
                }
                else {
                    Toast.makeText(getContext(), "Enter medicine name", Toast.LENGTH_SHORT).show();
                }

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        componentRecyclerView.setLayoutManager(linearLayoutManager);
        return root;
    }

    private void getMedicineDetails(String medicineName) {
        ApiServiceInterface api = ApiClient.getClient().create(ApiServiceInterface.class);
        Call<Root> call = api.getMedicineDetails(medicineName);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    showViews();
                    Root root = response.body();
                    if(root.status) {
                        ArrayList<MedicinesDetail> medicineDetails = root.medicinesDetails;
                        ArrayList<Component> components = medicineDetails.get(0).Components;
                        brandNamesTv.setText(root.medicinesDetails.get(0).brandName);
                        genericNameTv.setText(root.medicinesDetails.get(0).genericName);
                        accessionNumberTv.setText(root.medicinesDetails.get(0).accessionNumber);
                        descriptionTv.setText(root.medicinesDetails.get(0).Description);
                        ComponentAdapter adapter = new ComponentAdapter(components, getContext());
                        componentRecyclerView.setAdapter(adapter);
                        componentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        askDoctorButton.setVisibility(View.VISIBLE);
                    }
                    else {
                        askDoctorButton.setVisibility(View.INVISIBLE);
                        hideViews();
                        Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    hideViews();
                    Toast.makeText(getContext(), "Not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                // Handle network failure
                askDoctorButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
askDoctorButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.doctor_msg_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        EditText msgEdt = dialog.findViewById(R.id.dr_msg_edt);
        Button submitBtn = dialog.findViewById(R.id.dr_msg_btn);
        Button cancelBtn = dialog.findViewById(R.id.dr_cancel_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               //Execute Api Call
                askAdvice(medicineEditText.getText().toString(),msgEdt.getText().toString());
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
});
    }

    private void askAdvice(String medicine,String message) {
        ApiServiceInterface api = ApiClient.getClient().create(ApiServiceInterface.class);
        api.askAdvise(String.valueOf(getUserId(getContext())),medicine,message).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (root.status) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideViews() {
        brandNamesTv.setVisibility(View.GONE);
        brandHintTv.setVisibility(View.GONE);
        genericNameTv.setVisibility(View.GONE);
        genericHintTv.setVisibility(View.GONE);
        accessionNumberTv.setVisibility(View.GONE);
        accesssionHintTv.setVisibility(View.GONE);
        descriptionTv.setVisibility(View.GONE);
        descriptionHintTv.setVisibility(View.GONE);
        chemicalFormulaTv.setVisibility(View.GONE);
        chemicalFormulaHintTv.setVisibility(View.GONE);
        componentRecyclerView.setVisibility(View.GONE);
        componentHintTv.setVisibility(View.GONE);
    }

    public void showViews() {
        brandNamesTv.setVisibility(View.VISIBLE);
        brandHintTv.setVisibility(View.VISIBLE);
        genericNameTv.setVisibility(View.VISIBLE);
        genericHintTv.setVisibility(View.VISIBLE);
        accessionNumberTv.setVisibility(View.VISIBLE);
        accesssionHintTv.setVisibility(View.VISIBLE);
        descriptionTv.setVisibility(View.VISIBLE);
        descriptionHintTv.setVisibility(View.VISIBLE);
        chemicalFormulaTv.setVisibility(View.VISIBLE);
        chemicalFormulaHintTv.setVisibility(View.VISIBLE);
        componentRecyclerView.setVisibility(View.VISIBLE);
        componentHintTv.setVisibility(View.VISIBLE);
    }
}