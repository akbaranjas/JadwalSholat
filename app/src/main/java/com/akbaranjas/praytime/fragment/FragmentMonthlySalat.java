package com.akbaranjas.praytime.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akbaranjas.praytime.R;

/**
 * Created by akbaranjas on 08/12/16.
 */

public class FragmentMonthlySalat extends Fragment {
    public static FragmentMonthlySalat newInstance() {
        return new FragmentMonthlySalat();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monthly_salat, container, false);

        return rootView;
    }
}
