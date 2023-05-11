package com.android.pharmaapp.Retrofit;


import com.android.pharmaapp.models.Root;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET("medicineview.php")
    Call<Root> getMedicineDetails(@Query("name") String medicineName);



    @GET("history.php")
    Call<Root> getHistoryDetails(@Query("userid") String userId);
    @FormUrlEncoded
    @POST("user_registration.php")
    Call<Root> registerUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    //
    @FormUrlEncoded
    @POST("userlogin.php?")
    Call<Root> loginUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_token") String deviceToken
    );


    @FormUrlEncoded
    @POST("user_request.php")
    Call<Root> askAdvise(
            @Field("userid") String userId,
            @Field("med_name") String medName,
            @Field("message") String message
    );

    @FormUrlEncoded
    @POST("profileEdit.php")
    Call<Root> updateProfile(
            @Field("userid") String userId,
            @Field("Previous_illnesses") String prevIllness,
            @Field("Surgeries") String surgeries,
            @Field("Medications") String medications,
            @Field("Diet_information") String dietInformation,
            @Field("Exercise_habits") String exerciseHabits,
            @Field("Alcohol_use_information") String alcohol,
            @Field("Tobacco_use_information") String tobacco,
            @Field("Type_of_allergy") String typeOfAllergy,
            @Field("Symptoms_of_allergy") String symptomsOfAllergy,
            @Field("Severity_of_allergy") String severityOfAllergy,
            @Field("Symptom_onset_timeframe") String symptomOnSetTimeFrame,
            @Field("Allergy_history") String allergyHistory,
            @Field("Allergy_diagnosis") String allergyDiagnosis,
            @Field("Treatments_tried_for_allergy") String treatmentsForAllergy,
            @Field("Current_management_plan_for_allergy") String currentTreatment
    );
}


