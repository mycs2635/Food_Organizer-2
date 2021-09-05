package com.example.food_organizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonationFragment() {
        // Required empty public constructor
    }
    ImageView donate,collect;
    // for the donation page
    public static DonationFragment newInstance(String param1, String param2) {
        DonationFragment fragment = new DonationFragment(); // instantiating
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_donation, container, false);

        donate=v.findViewById(R.id.img_donate);
        collect=v.findViewById(R.id.img_collect);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override  // to go to the donate page
            public void onClick(View view) {
                Toast.makeText(getContext(), " Entering donation page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Donation.class));
            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            // to go to the collect page
            public void onClick(View view) {
                Toast.makeText(getContext(), " Entering collect page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Collect.class));
            }
        });
        return v;
    }
}