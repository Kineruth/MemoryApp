package memory.Memoryapp.Activity.GroupDiary;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import memory.Memoryapp.Holder.GroupDiaryDataHolder;
import memory.Memoryapp.Holder.ProfileDataHolder;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;


public class ProfileActivity extends AppCompatActivity {

    private TextView userName, userStatus;
    private CircleImageView profileImage;
    private Button addButton;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initFireBase();
        initFields();
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
    }


    private void initFields() {
        userName = findViewById(R.id.user_name_profile);
        userStatus = findViewById(R.id.user_status_profile);
        profileImage = findViewById(R.id.visit_profile_image);
        addButton = findViewById(R.id.add_to_group_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnAddButton();
            }
        });
        initProfileDetails();
    }

    private void initProfileDetails() {
        User user = ProfileDataHolder.getUserDataHolder().getUser();
        userName.setText(user.getName());
        userStatus.setText(user.getStatus());
        if(!user.getImage().isEmpty())
            Picasso.get().load(user.getImage()).into(profileImage);

    }

    private void clickOnAddButton() {
        final String groupId = GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().getUid();
        String profileIf = ProfileDataHolder.getUserDataHolder().getUser().getUid();
        GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().getGroupMember().add(profileIf);
        ProfileDataHolder.getUserDataHolder().getUser().getGroupId().add(groupId);
        mData.child("Users")
                .child(profileIf)
                .setValue(ProfileDataHolder.getUserDataHolder().getUser())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mData.child("Group Diary")
                                .child(groupId)
                                .setValue(GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        finish();
                                    }
                                });
                    }
                });
    }
