package memory.Memoryapp.Activity.GroupDiary;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import memory.Memoryapp.Adapter.TabAccessAdapter;
import memory.Memoryapp.Holder.GroupDiaryDataHolder;
import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.R;

public class GroupDiaryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabAccessAdapter myTabAccessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_diary);
        initFields();
    }

    private void initFields() {
        mToolbar = findViewById(R.id.group_diary_toolbar);
        setSupportActionBar(mToolbar);
        myViewPager = findViewById(R.id.group_tabs_pager);
        myTabAccessAdapter = new TabAccessAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabAccessAdapter);
        myTabLayout = findViewById(R.id.group_diary_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.group_diary_menu,menu);
        if(!GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().getAdmin().equals(UserDataHolder.getUserDataHolder().getUser().getUid())){
            MenuItem item = menu.findItem(R.id.add_members_option);
            item.setVisible(false);
        }
        Log.d("admin", GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().getAdmin());
        Log.d("user", UserDataHolder.getUserDataHolder().getUser().getUid());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_members_option){
            addMembersActivity();
        }
        if(item.getItemId() == R.id.add_group_memory_option){
            addMemoryActivity();
        }
        if(item.getItemId() == R.id.group_diary_search){
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }



    private void addMembersActivity() {
        Intent intent = new Intent(GroupDiaryActivity.this, AddMemberActivity.class);
        startActivity(intent);
    }

    private void addMemoryActivity() {
    }
}
