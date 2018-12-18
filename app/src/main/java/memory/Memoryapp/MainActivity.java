package memory.Memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MemoryApp");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.diaryFrame, new DiaryFragment())
                .commit();
        mAuth = FirebaseAuth.getInstance();
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
        if(item.getItemId() == R.id.main_create_group_option){
            createGroupActivity();

        }
        if(item.getItemId() == R.id.main_find_friends_option){

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
