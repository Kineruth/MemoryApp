package memory.Memoryapp.Activity.Memory;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;
import com.sangcomz.fishbun.define.Define;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.R;

public class AddPersonalMemoryActivity extends AppCompatActivity {

    private EditText descriptionEditText;
    private EditText nameEditText;
    private ImageView memoryImageView;
    private DatabaseReference mData;
    private StorageReference personalDiaryImageRef;
    private String key;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_memory);
        initFields();
        initFireBase();
    }

    private void initFields(){
        descriptionEditText = findViewById(R.id.description);
        descriptionEditText.setMovementMethod(new ScrollingMovementMethod());
        nameEditText = findViewById(R.id.memory_name);
        memoryImageView = findViewById(R.id.memory_image);
        findViewById(R.id.add_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnAddMemory();
            }
        });
        memoryImageView = findViewById(R.id.memory_image);
        memoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnMemoryImage();
            }
        });
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
        key = mData.child("Personal Diary").child(UserDataHolder.getUserDataHolder().getUser().getUid()).child("Memories").push().getKey();
        personalDiaryImageRef = FirebaseStorage.getInstance().getReference().child("Personal Memory").child(UserDataHolder.getUserDataHolder().getUser().getUid());

    }

    private void clickOnMemoryImage() {
        FishBun.with(this).setImageAdapter(new GlideAdapter())
                .setMinCount(1)
                .setMaxCount(1)
                .startAlbum();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Define.ALBUM_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            imageUri = (Uri)data.getParcelableArrayListExtra(Define.INTENT_PATH).get(0);
            Picasso.get().load(imageUri.toString()).into(memoryImageView);
        }
    }

    private void clickOnAddMemory() {
        final Memory memory = new Memory(key,
                nameEditText.getText().toString(),
                descriptionEditText.getText().toString(),
                Calendar.getInstance().getTimeInMillis(),
                "");
        if(imageUri != null) {
            final StorageReference filePath = personalDiaryImageRef.child(key + ".jpg");
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            memory.setImage(imageUri.toString());
                            mData.child("Personal Diary").child(UserDataHolder.getUserDataHolder().getUser().getUid()).child("Memories")
                                    .child(key).setValue(memory).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }
        else{
            mData.child("Personal Diary").child(UserDataHolder.getUserDataHolder().getUser().getUid()).child("Memories")
                    .child(key).setValue(memory).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    finish();
                }
            });
        }
    }
}
