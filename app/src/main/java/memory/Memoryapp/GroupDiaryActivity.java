package memory.Memoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class GroupDiaryActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_diary);
        initFireBase();
    }

    private void initFireBase() {
        mToolbar = findViewById(R.id.group_diary_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.group_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_members_option){
            addMembersActivity();
        }
        if(item.getItemId() == R.id.add_memory_option){
            addMemoryActivity();
        }
        return true;
    }



    private void addMembersActivity() {

    }

    private void addMemoryActivity() {
    }
}
