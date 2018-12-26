package memory.Memoryapp.Activity.Memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import memory.Memoryapp.Holder.MemoryDataHolder;
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.R;


public class MemoryActivity extends AppCompatActivity {

    private TextView descriptionEditText;
    private TextView nameEditText;
    private ImageView memoryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        initFields();
    }

    private void initFields(){
        descriptionEditText = findViewById(R.id.description_profile);
        descriptionEditText.setMovementMethod(new ScrollingMovementMethod());
        nameEditText = findViewById(R.id.memory_name_profile);
        memoryImageView = findViewById(R.id.memory_image);
        initMemoryDetails();
    }

    private void initMemoryDetails() {
        Memory memory = MemoryDataHolder.getMemoryDataHolder().getMemory();
        nameEditText.setText(memory.getMemoryName());
        descriptionEditText.setText(memory.getDescription());
        Picasso.get().load(memory.getImage()).into(memoryImageView);
    }
}
