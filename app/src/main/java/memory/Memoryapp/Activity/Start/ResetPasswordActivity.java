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
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import memory.Memoryapp.R;

/**
 * This class represents all the reset Password activities.
 */
public class ResetPasswordActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty()
    @Email()
    private EditText email;
    private Validator validator;
    private static boolean valIsDone;
    private FirebaseAuth mAuth;

    /**
     * In case the activity needs to be recreated - the saved state can be passed back to onCreate
     * so there is no lost of this prior information.
     * @param savedInstanceState a bundle of a saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initFields();
        initValidator();
        initFireBase();
    }

    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields(){
        email = findViewById(R.id.etResetEmail);
        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnBtnReset();
            }
        });
    }

    /**
     *
     */
    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    /**
     * Gets the firebase instances & references.
     */
    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * When the 'Reset Password' button was clicked.
     */
    private void clickOnBtnReset() {
        validator.validate();
        if(valIsDone){
            final String mail = email.getText().toString();
<<<<<<< HEAD
            loadingBar.setTitle("Reset Password");
            loadingBar.setMessage("Please wait while we reset your password...");
            loadingBar.show();
=======
>>>>>>> master
            mAuth.sendPasswordResetEmail(mail)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
<<<<<<< HEAD
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                loadingBar.dismiss();
                                loginActivity();
                            }
                            else{ //failed
                                Toast.makeText(ResetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
=======
                        public void onSuccess(Void aVoid) {
                            loginActivity();
>>>>>>> master
                        }
                    });
        }
    }

    /**
     * Validation succeeded.
     */
    @Override
    public void onValidationSucceeded() {
        valIsDone = true;
    }

    /**
     * Validation failed - sends errors messages.
     * @param errors all the errors to be shown.
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

    /**
     * Connects to login activity.
     * An intent - basically a message to say you did or want something to happen.
     */
    private void loginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
