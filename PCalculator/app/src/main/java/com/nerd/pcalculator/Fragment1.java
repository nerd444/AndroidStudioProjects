package com.nerd.pcalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }

    EditText edit_text;
    TextView txt_result;
    Button btn_clear;
    Button btn_record;

    Button btn_division;
    Button btn_multi;
    Button btn_minus;
    Button btn_plus;

    Button btn_cancle;
    Button btn_result;

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        edit_text = view.findViewById(R.id.edit_text);
        txt_result = view.findViewById(R.id.txt_result);
        btn_clear = view.findViewById(R.id.btn_clear);
        btn_record = view.findViewById(R.id.btn_record);
        btn_division = view.findViewById(R.id.btn_division);
        btn_multi = view.findViewById(R.id.btn_multi);
        btn_minus = view.findViewById(R.id.btn_minus);
        btn_plus = view.findViewById(R.id.btn_plus);
        btn_cancle = view.findViewById(R.id.btn_cancle);
        btn_result = view.findViewById(R.id.btn_result);
        btn_0 = view.findViewById(R.id.btn_0);
        btn_1 = view.findViewById(R.id.btn_1);
        btn_2 = view.findViewById(R.id.btn_2);
        btn_3 = view.findViewById(R.id.btn_3);
        btn_4 = view.findViewById(R.id.btn_4);
        btn_5 = view.findViewById(R.id.btn_5);
        btn_6 = view.findViewById(R.id.btn_6);
        btn_7 = view.findViewById(R.id.btn_7);
        btn_8 = view.findViewById(R.id.btn_8);
        btn_9 = view.findViewById(R.id.btn_9);

        return view;
    }
}