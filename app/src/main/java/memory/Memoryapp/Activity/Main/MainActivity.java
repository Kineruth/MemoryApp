package memory.Memoryapp.Activity.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

    /**
     * In case the activity needs to be recreated - the saved state can be passed back to onCreate
     * so there is no lost of this prior information.
     * @param savedInstanceState a bundle of a saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFireBase();
        initFields();
        initUser();
    }

    /**
     * Called when continuing the app when it is paused.
     */
    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
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
            /**
             * Checks if this view can be scrolled vertically in a certain direction.
             * @return false for scrolling up, true for down.
             */
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

    /**
     * Gets the firebase instances & references.
     */
    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Initializing the user.
     */
    private void initUser() {
        mData.child("Users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userTemp = dataSnapshot.getValue(User.class);
                UserDataHolder.getUserDataHolder().getUser().setAll(userTemp);
                initRecyclerView();
            }

            /**
             *  when an error occurs.
             * @param databaseError the errors.
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Initializing a recycle view.
     */
    private void initRecyclerView() {
        mData.child("Group Diary").addValueEventListener(new ValueEventListener() {
            /**
             * It is triggered once when the listener is attached,
             * and again every time the data, including children, changes.
             * @param dataSnapshot the data.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupDiaryList.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    GroupDiary groupDiary = data.getValue(GroupDiary.class);
                    if(UserDataHolder.getUserDataHolder().getUser().getGroupId().contains(groupDiary.getUid()))
                        groupDiaryList.add(groupDiary);
                }
                groupDiaryAdapter.notifyDataSetChanged();
            }

            /**
             * when an error occurs.
             * @param databaseError the errors.
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mData.child("Personal Diary").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {

            /**
             * It is triggered once when the listener is attached,
             * and again every time the data, including children, changes.
             * @param dataSnapshot the data.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personalDiaryList.clear();
                PersonalDiary personalDiary = dataSnapshot.getValue(PersonalDiary.class);
                personalDiaryList.add(personalDiary);
                personalDiaryAdapter.notifyDataSetChanged();
            }

            /**
             * when an error occurs.
             * @param databaseError the errors.
             */
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
        getMenuInflater().inflate(R.menu.options_menu,menu);
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

    /**
     * Connects to the group activity and starts it.
     */
    private void createGroupActivity() {
        Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
        startActivity(intent);
    }

    /**
     * Connects to the settings activity and starts it.
     */
    private void settingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Connects to login activity and starts it.
     * An intent - basically a message to say you did or want something to happen.
     */
    private void loginActivity() {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
