package memory.Memoryapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    @NotEmpty
    private EditText groupName;
    private Validator validator;
    private static boolean valIsDone;
    private CircleImageView groupProfileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        findViewById(R.id.create_group_button).setOnClickListener(this);
        groupName = findViewById(R.id.set_group_name);
        groupProfileImage = findViewById(R.id.set_group_image);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        loadingBar = new ProgressDialog(this);
    }

    @Override
    public void onValidationSucceeded() {
        valIsDone = true;
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
    public void onClick(View v) {
        validator.validate();
        if(valIsDone && v.getId() == R.id.create_group_button){
            createGroup();
        }
    }

    private void createGroup() {
        loadingBar.setTitle("Create Group");
        loadingBar.setMessage("Please wait, while we are create your new group for you...");
        loadingBar.show();
        String groupname = groupName.getText().toString();
        mData.child("Groups").child(groupname).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            loadingBar.dismiss();
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(CreateGroupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
