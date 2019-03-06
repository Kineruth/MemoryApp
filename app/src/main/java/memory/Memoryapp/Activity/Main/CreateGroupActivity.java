package memory.Memoryapp.Activity.Main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import memory.Memoryapp.Holder.UserDataHolder;
import memory.Memoryapp.Object.GroupDiary;
import memory.Memoryapp.R;

/**
 * This class represents all the activities for creating a group.
 */
public class CreateGroupActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText groupName;
    private Validator validator;
    private static boolean valIsDone;
    private CircleImageView groupProfileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private StorageReference groupImageRef;
    private String key;
    private Uri imageUrl = null;

    /**
     * In case the activity needs to be recreated - the saved state can be passed back to onCreate
     * so there is no lost of this prior information.
     * @param savedInstanceState a bundle of a saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initFields();
        initValidator();
        initFireBase();
    }

    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields(){
        findViewById(R.id.create_group_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnCreateGroupButton();
            }
        });
        groupName = findViewById(R.id.set_group_name);
        groupProfileImage = findViewById(R.id.set_group_image);
        groupProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnset_group_image();
            }
        });
    }


    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    /**
     * Gets the groupDiary images and adds a new key - groups.
     */
    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        groupImageRef = FirebaseStorage.getInstance().getReference().child("GroupDiary Images");
        key = mData.child("Groups").push().getKey();

    }
/**
 * When clicking on 'Create Group' button.
 */
    private void clickOnCreateGroupButton(){
        validator.validate();
        if (valIsDone) {
            final GroupDiary groupDiary = new GroupDiary(groupName.getText().toString(), "", key, mAuth.getCurrentUser().getUid());
            if (imageUrl != null) {
                final StorageReference filePath = groupImageRef.child(key + ".jpg");
                filePath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                groupDiary.setImage(uri.toString());
                                mData.child("Group Diary").child(key).setValue(groupDiary)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                UserDataHolder.getUserDataHolder().getUser().getGroupId().add(key);
                                                mData.child("Users").child(mAuth.getCurrentUser().getUid())
                                                        .setValue(UserDataHolder.getUserDataHolder().getUser())
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
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
                });
            }
            else {
                mData.child("Group Diary").child(key).setValue(groupDiary)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                UserDataHolder.getUserDataHolder().getUser().getGroupId().add(key);
                                mData.child("Users").child(mAuth.getCurrentUser().getUid())
                                        .setValue(UserDataHolder.getUserDataHolder().getUser())
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                finish();
                                            }
                                        });
                            }
                        });
            }
        }
    }

    private void clickOnset_group_image() {
        FishBun.with(this).setImageAdapter(new GlideAdapter())
                .setMinCount(1)
                .setMaxCount(1)
                .startAlbum();
    }

    /**
     * Calling this method when starting another activity from current to get the result for it
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Define.ALBUM_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri imageUri = (Uri)data.getParcelableArrayListExtra(Define.INTENT_PATH).get(0);
            CropImage.activity(imageUri)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                imageUrl = result.getUri();
                Picasso.get().load(imageUrl.toString()).into(groupProfileImage);

            }
        }
    }

    /**
     * Called when all the 'Rules' added to this Validator are valid.
     */
    @Override
    public void onValidationSucceeded() {
        valIsDone = true;
    }

    /**
     * Called if any of the Rules fail.
     * @param errors the errors.
     */
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
}
