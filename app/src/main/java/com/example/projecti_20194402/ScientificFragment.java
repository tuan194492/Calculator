package com.example.projecti_20194402;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ScientificFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scientific, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello world cai cc");
            }
        });
        // Trinogometry View
        getView().findViewById(R.id.trinoBtn).setOnClickListener(this);
        getView().findViewById(R.id.functionBtn).setOnClickListener(this);
        getView().setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
//        getView().findViewById(R.id.toggleBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View target = getView().findViewById(R.id.target);
//                if (target.getVisibility() == View.VISIBLE)
//                    target.setVisibility(View.GONE);
//                else
//                    target.setVisibility(View.VISIBLE);
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        View trinoView = getView().findViewById(R.id.trinoView);
        View functionView = getView().findViewById(R.id.functionView);
        if (v.getId() == R.id.trinoBtn) {
            functionView.setVisibility(View.GONE);
            if (trinoView.getVisibility() == View.GONE)
                trinoView.setVisibility(View.VISIBLE);
            else
                trinoView.setVisibility(View.GONE);
            return;
        } else {
            trinoView.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.functionBtn) {
            if (functionView.getVisibility() == View.GONE)
                functionView.setVisibility(View.VISIBLE);
            else
                functionView.setVisibility(View.GONE);
            return;
        } else {
            functionView.setVisibility(View.GONE);
        }
    }
}