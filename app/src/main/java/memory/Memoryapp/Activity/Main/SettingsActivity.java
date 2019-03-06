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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;
import com.sangcomz.fishbun.define.Define;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

public class SettingsActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Pattern(message = "Input must contain only letters", regex = "[a-zA-Z][a-zA-Z ]+")
    private EditText userName;
    @NotEmpty
    private  EditText userStatus;
    private Validator validator;
    private static boolean valIsDone;
    private CircleImageView userProfileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private StorageReference userProfileImageRef;
    private String downloaedUrl;

    /**
     * In case the activity needs to be recreated - the saved state can be passed back to onCreate
     * so there is no lost of this prior information.
     * @param savedInstanceState a bundle of a saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initFields();
        initValidator();
        initFireBase();
        initSettings();

    }

    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields(){
        findViewById(R.id.update_settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnupdate_settings_button();
            }
        });
        userName = findViewById(R.id.set_user_name);
        userStatus = findViewById(R.id.set_profile_status);
        userProfileImage = findViewById(R.id.set_profile_image);
        userProfileImage.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                clickOnset_profile_image();
            }
        });
    }

    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    /**
     * Gets the firebase instances & references.
     */
    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
    }

    private void initSettings() {
        mData.child("Users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * It is triggered once when the listener is attached,
             * and again every time the data, including children, changes.
             * @param dataSnapshot the data.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userName.setText(user.getName());
                userStatus.setText(user.getStatus());
                if(!user.getImage().equals(""))
                    Picasso.get().load(user.getImage()).into(userProfileImage);

            }

            /**
             * when an error occurrs.
             * @param databaseError the error.
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Called when clicked on 'update settings' button.
     */
    private void clickOnupdate_settings_button() {
        validator.validate();
        if(valIsDone) {
            String setName = userName.getText().toString();
            String setStatus = userStatus.getText().toString();
            final String uid = mAuth.getCurrentUser().getUid();
            final User user = new User(setName, setStatus, uid, downloaedUrl);
            mData.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    finish();
                }
            });
        }
    }

    /**
     * Called when clicked on 'set profile image'.
     */
    private void clickOnset_profile_image() {
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
        if (requestCode == Define.ALBUM_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = (Uri) data.getParcelableArrayListExtra(Define.INTENT_PATH).get(0);
            CropImage.activity(imageUri)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                final StorageReference filePath = userProfileImageRef.child(mAuth.getCurrentUser().getUid() + ".jpg");
                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloaedUrl = uri.toString();
                                Picasso.get().load(downloaedUrl).into(userProfileImage);
                            }
                        });
                    }
                });
            }
        }
    }
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
