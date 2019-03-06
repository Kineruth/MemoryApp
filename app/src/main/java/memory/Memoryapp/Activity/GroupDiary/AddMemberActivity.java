package memory.Memoryapp.Activity.GroupDiary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import memory.Memoryapp.Adapter.UserAdapter;
import memory.Memoryapp.Holder.GroupDiaryDataHolder;
import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;


public class AddMemberActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private android.support.v7.widget.Toolbar mToolbar;
    private RecyclerView addMemberRecyclerView;
    private DatabaseReference mData;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        initFireBase();
        initFields();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void initFields() {
        mToolbar = findViewById(R.id.add_member_toolbar);
        setSupportActionBar(mToolbar);
        addMemberRecyclerView = findViewById(R.id.add_member_recyclerlist);
        addMemberRecyclerView.setHasFixedSize(true);
        addMemberRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        addMemberRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_member_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_member);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void initRecyclerView() {
        mData.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);
                    if(!GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().getGroupMember().contains(user.getUid()))
                        userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        userAdapter.filterList(s);
        return true;
    }
}
