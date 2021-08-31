package com.example.food_organizer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    private DatabaseReference ref;

    Button add;

    private ArrayList<Images> imageList;
    private RecyclerAdaptor recyclerAdaptor;
    private Context homeContext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private void getDataFromFirebase() {

        Query query = ref.child("images");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             //   clearAll();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Images images = new Images();
                    images.setImageUrl(dataSnapshot.child("imageUrl").getValue().toString());
                    images.setImageName(dataSnapshot.child("imageName").getValue().toString());
                    imageList.add(images);
                }
                try {

                    recyclerAdaptor = new RecyclerAdaptor(getContext(), imageList);
                    recyclerView.setAdapter(recyclerAdaptor);
                    recyclerAdaptor.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                recyclerView.addOnItemTouchListener(new RcyclrTouchListnr(getContext(),recyclerView,new Clicklstnr(){


                    @Override
                    public void onClick(View view, int pos) {

                    }

                    @Override
                    public void onLongClick(View view, int pos) {

                    }
                }));

            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void clearAll(){
        if(imageList != null){
            imageList.clear();

            if(recyclerAdaptor != null){
                recyclerAdaptor.notifyDataSetChanged();
            }
        }
        imageList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);
        ref = FirebaseDatabase.getInstance().getReference();

        imageList = new ArrayList<>();
        clearAll();

        // get data from firebase database
        getDataFromFirebase();

        add=view.findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),CreateProductPage.class));
            }
        });
        return view;
    }
    public static interface Clicklstnr{
        public void onClick(View view,int pos);
        public void onLongClick(View view,int pos);

    }

    class RcyclrTouchListnr implements RecyclerView.OnItemTouchListener {
        private Clicklstnr clicklistener;
        private GestureDetector gestureDetector;

        public RcyclrTouchListnr(Context context, RecyclerView recyclerView, Clicklstnr clicklstnr) {

        }

        public void RecyclerTouchListener(Context context, final RecyclerView recycleView, final Clicklstnr clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}