package memory.Memoryapp.Activity.PersonalDiary;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import memory.Memoryapp.R;

/**
 * This class represents all of the user's personal diary activities.
 */
public class PersonalDiaryActivity extends AppCompatActivity {

    private Toolbar mToolbar;

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
    }
    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields() {
        mToolbar = findViewById(R.id.personal_diary_toolbar);
        setSupportActionBar(mToolbar);
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
        }
        if(item.getItemId() == R.id.personal_diary_search){
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
