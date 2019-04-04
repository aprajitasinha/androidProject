package com.mobiloitte.androidm3retest.Fragments;



import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mobiloitte.androidm3retest.Adapter.RecyclerViewMissCallAdapter;
import com.mobiloitte.androidm3retest.R;
import com.mobiloitte.androidm3retest.SwipeDelete;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissCallFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerViewMissCallAdapter mAdapter;
    ArrayList<String> stringArrayList1 = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;


    public  MissCallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_call_history,null);


        recyclerView = v.findViewById(R.id.recyclerView);
        coordinatorLayout = v.findViewById(R.id.coordinatorLayout);

        populateRecyclerView();
        enableSwipeToDeleteAndUndo();
        return  v;    }

    private void populateRecyclerView() {
        stringArrayList1.add("John Thomas");
        stringArrayList1.add("Jenny Koul");
        stringArrayList1.add("Mike Keel");
        stringArrayList1.add("Julia Kite");
        stringArrayList1.add("Tiny Tim");
        stringArrayList1.add("Kelly Kim");
        stringArrayList1.add("Jenny Koul");
        stringArrayList1.add("Mike Keel");
        mAdapter = new RecyclerViewMissCallAdapter(stringArrayList1);
        recyclerView.setAdapter(mAdapter);


    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeDelete swipeToDeleteCallback = new SwipeDelete( getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor( Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}

