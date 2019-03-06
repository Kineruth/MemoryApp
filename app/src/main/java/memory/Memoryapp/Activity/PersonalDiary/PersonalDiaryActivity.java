package memory.Memoryapp.Activity.PersonalDiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import memory.Memoryapp.Adapter.MemoryAdapter;
import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.R;

/**
 * This class represents all of the user's personal diary activities.
 */
public class PersonalDiaryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView memoryRecyclerView;
    private MemoryAdapter adapter;
    private List<Memory> memoryList;
    private DatabaseReference mData;

    /**
     * In case the activity needs to be recreated - the saved state can be passed back to onCreate
     * so there is no lost of this prior information.
     * @param savedInstanceState a bundle of a saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_diary);
        initFields();
        initFireBase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }
    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields() {
        mToolbar = findViewById(R.id.personal_diary_toolbar);
        setSupportActionBar(mToolbar);
        memoryList = new ArrayList<>();
        memoryRecyclerView = findViewById(R.id.MemoryPersonalDiaryRecyclerView);
        memoryRecyclerView.setHasFixedSize(true);
        memoryRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new MemoryAdapter(this, memoryList);
        memoryRecyclerView.setAdapter(adapter);
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void initRecyclerView() {
        mData.child("Personal Diary").child(UserDataHolder.getUserDataHolder().getUser().getUid()).child("Memories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memoryList.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Memory memory = data.getValue(Memory.class);
                    Log.d("test", memory.toString());
                    memoryList.add(memory);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Used to specify the options menu for an activity
     * @param menu a given menu to be displayed.
     * @return true to be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.personal_diary_menu,menu);
        return true;
    }

    /**
     * Checked what was chosen - adding or searching in the personal diary.
     * @param item an item that has been selected.
     * @return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_personal_memory_option){
            addMemoryActivity();
        }
        if(item.getItemId() == R.id.personal_diary_search){
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void addMemoryActivity() {
        Intent intent = new Intent(this, AddPersonalMemoryActivity.class);
        startActivity(intent);
    }
}
