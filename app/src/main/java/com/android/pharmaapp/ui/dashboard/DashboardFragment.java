package com.android.pharmaapp.ui.dashboard;

import static com.android.pharmaapp.AndroidUtil.pickDateandTimeFormat;
import static com.android.pharmaapp.AndroidUtil.pickTime;
import static com.android.pharmaapp.AndroidUtil.pickTimeFormat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.pharmaapp.R;
import com.android.pharmaapp.databinding.FragmentDashboardBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button mButton = root.findViewById(R.id.save_button);
        EditText startDate=root.findViewById(R.id.medicine_time_input);
        EditText endDate=root.findViewById(R.id.end_time_input);
        EditText medicineName=root.findViewById(R.id.medicine_name_input);


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateandTimeFormat(getContext(),startDate);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateandTimeFormat(getContext(),endDate);
            }
        });
// Event start and end time with date
        String startTime = "2022-02-1T09:00:00";
        String endTime = "2022-02-1T12:00:00";

// Parsing the date and time
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date mStartTime = null;
        Date mEndTime = null;
        try {
            mStartTime = mSimpleDateFormat.parse(startTime);
            mEndTime = mSimpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date finalMStartTime = mStartTime;
        Date finalMEndTime = mEndTime;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_EDIT);
                mIntent.setType("vnd.android.cursor.item/event");
                mIntent.putExtra("beginTime",startDate.getText().toString());
                mIntent.putExtra("time", true);
                mIntent.putExtra("rule", "FREQ=YEARLY");
                mIntent.putExtra("endTime", finalMEndTime.getTime());
                mIntent.putExtra("title", medicineName.getText().toString());
                startActivity(mIntent);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}