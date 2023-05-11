package com.android.pharmaapp.ui;

import static com.android.pharmaapp.AndroidUtil.getUserId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.pharmaapp.LoginActivity;
import com.android.pharmaapp.R;
import com.android.pharmaapp.Retrofit.ApiClient;
import com.android.pharmaapp.Retrofit.ApiServiceInterface;
import com.android.pharmaapp.SignupActivity;
import com.android.pharmaapp.adapters.HistoryAdapter;
import com.android.pharmaapp.models.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {
RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView=root.findViewById(R.id.history_view);
        getHistory(getActivity());

        return root;
    }

    public void getHistory(Context context){
        ApiServiceInterface api = ApiClient.getClient().create(ApiServiceInterface.class);
        api.getHistoryDetails(String.valueOf(getUserId(context))).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (root.status) {
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HistoryAdapter historyAdapter=new HistoryAdapter(root.userHistory,getContext());
                    recyclerView.setAdapter(historyAdapter);

                } else {
                    Toast.makeText(context, "Registration failed.Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    }
