package memory.Memoryapp.Activity.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import memory.Memoryapp.Activity.Start.LoginActivity;
import memory.Memoryapp.Adapter.GroupDiaryAdapter;
import memory.Memoryapp.Adapter.PersonalDiaryAdapter;
import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.GroupDiary;
import memory.Memoryapp.Object.PersonalDiary;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private RecyclerView groupRecyclerView;
    private RecyclerView personalRecyclerView;
    private PersonalDiaryAdapter personalDiaryAdapter;
    private GroupDiaryAdapter groupDiaryAdapter;
    private List<GroupDiary> groupDiaryList;
    private List<PersonalDiary> personalDiaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFireBase();
        initFields();
        initUser();
    }

    private void initFields(){
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        groupDiaryList = new ArrayList<>();
        personalDiaryList = new ArrayList<>();
        groupRecyclerView = findViewById(R.id.GroupDiaryRecyclerView);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personalRecyclerView = findViewById(R.id.PersonalDiaryRecyclerView);
        personalRecyclerView.setHasFixedSize(true);
        personalRecyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        groupDiaryAdapter = new GroupDiaryAdapter(this, groupDiaryList);
        groupRecyclerView.setAdapter(groupDiaryAdapter);
        personalDiaryAdapter = new PersonalDiaryAdapter(this, personalDiaryList);
        personalRecyclerView.setAdapter(personalDiaryAdapter);
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initUser() {
        mData.child("Users").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userTemp = dataSnapshot.getValue(User.class);
                UserDataHolder.getUserDataHolder().getUser().setAll(userTemp);
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        groupDiaryList.clear();
        mData.child("Group Diary").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    GroupDiary groupDiary = data.getValue(GroupDiary.class);
                    if(UserDataHolder.getUserDataHolder().getUser().getGroupId().contains(groupDiary.getUid()))
                        groupDiaryList.add(groupDiary);
                }
                groupDiaryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        personalDiaryList.clear();
        mData.child("Personal Diary").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalDiary personalDiary = dataSnapshot.getValue(PersonalDiary.class);
                personalDiaryList.add(personalDiary);
                personalDiaryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_logout_option){
            loginActivity();
        }
        if(item.getItemId() == R.id.main_setting_option){
            settingsActivity();
        }
        if(item.getItemId() == R.id.main_create_group_option) {
            createGroupActivity();
        }
        return true;
    }

    private void createGroupActivity() {
        Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
        startActivity(intent);
    }

    private void settingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void loginActivity() {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
