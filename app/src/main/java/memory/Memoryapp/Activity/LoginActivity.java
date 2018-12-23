package memory.Memoryapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import memory.Memoryapp.R;

public class LoginActivity extends Activity implements Validator.ValidationListener {


    @NotEmpty()
    @Email()
    private EditText email;
    @NotEmpty()
    @Password(min = 1, message = "Minimum 6 characters")
    private EditText password;
    private Validator validator;
    private static boolean valIsDone;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFields();
        initValidator();
        initFireBase();

    }

    private void initFields(){
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnbtnLogin();
            }
        });
        findViewById(R.id.tvRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOntvRegister();
            }
        });
        findViewById(R.id.tvResetPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOntvResetPassword();
            }
        });
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        loadingBar = new ProgressDialog(this);
    }

    private void clickOnbtnLogin() {
        validator.validate();
        if(valIsDone) {
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are login to your account for you...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void initValidator(){
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
    }

    private void clickOntvRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        clearError();
    }

    private void clickOntvResetPassword() {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
        clearError();
    }

    private void clearError() {
        email.setError(null);
        password.setError(null);
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
}
