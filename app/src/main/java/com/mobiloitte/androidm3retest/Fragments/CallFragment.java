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

import com.mobiloitte.androidm3retest.Adapter.RecyclerViewAdapter;
import com.mobiloitte.androidm3retest.R;
import com.mobiloitte.androidm3retest.SwipeDelete;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;


    public CallFragment() {
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
        stringArrayList.add("John Thomas");
        stringArrayList.add("Jenny Koul");
        stringArrayList.add("Mike Keel");
        stringArrayList.add("Julia Kite");
        stringArrayList.add("Tiny Tim");
        stringArrayList.add("Kelly Kim");
        stringArrayList.add("Jenny Koul");
        stringArrayList.add("Mike Keel");
        mAdapter = new RecyclerViewAdapter(stringArrayList);
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

