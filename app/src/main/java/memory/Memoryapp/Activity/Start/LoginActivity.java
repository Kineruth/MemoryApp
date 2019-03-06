package memory.Memoryapp.Activity.Start;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import memory.Memoryapp.Activity.Main.MainActivity;
import memory.Memoryapp.R;

/**
 * This class represents all the login activities.
 */
public class LoginActivity extends Activity implements Validator.ValidationListener {

    @NotEmpty
    @Email()
    private EditText email;
    @NotEmpty()
    @Password(message = "Minimum 6 Characters")
    private EditText password;
    private Validator validator;
    private static boolean valIsDone;
    private FirebaseAuth mAuth;

    /**
     * The bundle can be passed back to onCreate if the activity needs to be recreated
     * and we won't lose this prior information
     * @param savedInstanceState th saved state of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFields();
        initValidator();
        initFireBase();

    }

    /**
     * Initialization the connection of the fields in xml file to their activities.
     */
    private void initFields(){
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v the view that has been clicked.
             */
            @Override
            public void onClick(View v) {
                clickOnBtnLogin();
            }
        });
        findViewById(R.id.tvRegister).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v the view that has been clicked.
             */
            @Override
            public void onClick(View v) {
                clickOntvRegister();
            }
        });
        findViewById(R.id.tvResetPassword).setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * @param v the view that has been clicked.
             */
            @Override
            public void onClick(View v) {
                clickOntvResetPassword();
            }
        });
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
    }

    /**
     * When the button 'Login' was clicked.
     */
    private void clickOnBtnLogin() {
        validator.validate();
        if(valIsDone) {
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                  mainActivity();
                }
            });
        }
    }

    private void mainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    /**
     * Gets the firebase instances.
     */
    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * When clicked on the 'Register' button.
     */
    private void clickOntvRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        clearError();
    }

    /**
     * When clicked on the 'Reset Password' button.
     * An intent - basically a message to say you did or want something to happen.
     */
    private void clickOntvResetPassword() {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
        clearError();
    }

    /**
     * Sets the error message to null - so here is no error.
     */
    private void clearError() {
        email.setError(null);
        password.setError(null);
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
}
