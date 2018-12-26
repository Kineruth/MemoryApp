package memory.Memoryapp.Activity.Start;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

import memory.Memoryapp.Object.PersonalDiary;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty()
    @Email()
    private EditText email;
    @NotEmpty()
    @Password(message = "Minimum 6 characters")
    private EditText password;
    @NotEmpty
    @Pattern(message = "Input must contain only letters", regex = "[a-zA-Z][a-zA-Z ]+")
    private EditText fullname;
    private Validator validator;
    private static boolean valIsDone;
    private FirebaseAuth mAuth;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initFields();
        initValidator();
        initFireBase();

    }

    private void initFields(){
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        fullname = findViewById(R.id.etFullName);
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnbtnRegister();
            }
        });
    }

    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
    }

    private void clickOnbtnRegister() {
        validator.validate();
        if(valIsDone){
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            final String name = fullname.getText().toString();
            mAuth.createUserWithEmailAndPassword(mail,pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mData.child("Users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(new User(name,mAuth.getCurrentUser().getUid()))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            mData.child("Personal Diary")
                                                    .child(mAuth.getCurrentUser().getUid())
                                                    .setValue(new PersonalDiary("My Diary", "", mData.child("Personal Diary").push().getKey()))
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            loginActivity();
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

    private void loginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

