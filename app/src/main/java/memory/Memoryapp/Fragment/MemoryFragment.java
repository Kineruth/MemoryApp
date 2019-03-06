package memory.Memoryapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import memory.Memoryapp.Adapter.MemoryAdapter;
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    private RecyclerView memoryRecyclerView;
    private MemoryAdapter adapter;
    private List<Memory> memoryList;
    private View view;
    private DatabaseReference mData;

    public MemoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memory, container, false);
        // Inflate the layout for this fragment
        initFields();
        initFireBase();
        initRecyclerView();
        return view;
    }

    private void initFields(){
        memoryList = new ArrayList<>();
        memoryRecyclerView = view.findViewById(R.id.MemoryGroupDiaryRecyclerView);
        memoryRecyclerView.setHasFixedSize(true);
        memoryRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        adapter = new MemoryAdapter(this.getContext(), memoryList);
        memoryRecyclerView.setAdapter(adapter);
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void initRecyclerView() {

    }

}
