package memory.Memoryapp.Activity.PersonalDiary;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;
import com.sangcomz.fishbun.define.Define;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.R;

public class AddPersonalMemoryActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty()
    private EditText descriptionEditText;
    @NotEmpty()
    private EditText nameEditText;
    private ImageView memoryImageView;
    private DatabaseReference mData;
    private StorageReference personalDiaryImageRef;
    private String key;
    private Uri imageUri = null;
    private Validator validator;
    private static boolean valIsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_memory);
        initFields();
        initFireBase();
        initValidator();
    }

    private void initFields(){
        descriptionEditText = findViewById(R.id.add_description);
        descriptionEditText.setMovementMethod(new ScrollingMovementMethod());
        nameEditText = findViewById(R.id.memory_name);
        memoryImageView = findViewById(R.id.memory_image);
        findViewById(R.id.add_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnAddMemory();
            }
        });
        memoryImageView = findViewById(R.id.add_memory_image);
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

    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
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
        validator.validate();
        if(valIsDone && imageUri != null){
            final Memory memory = new Memory(key,
                nameEditText.getText().toString(),
                descriptionEditText.getText().toString(),
                Calendar.getInstance().getTimeInMillis(),
                "");
            final StorageReference filePath = personalDiaryImageRef.child(key + ".jpg");
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            memory.setImage(uri.toString());
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
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        valIsDone = false;
        for(ValidationError error: errors){
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if(view instanceof EditText){
                ((EditText)view).setError(message);
            }
            else{
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        valIsDone = true;
    }
}
