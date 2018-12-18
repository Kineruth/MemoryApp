package memory.Memoryapp;

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
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class LoginActivity extends Activity implements View.OnClickListener, Validator.ValidationListener {


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
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.tvRegister).setOnClickListener(this);
        findViewById(R.id.tvResetPassword).setOnClickListener(this);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        validator.validate();
        if (valIsDone && v.getId() == R.id.btnLogin) {
            loginAccount();
        }
        else if (v.getId() == R.id.tvRegister) {
            registerActivity();
        }
        else if (v.getId() == R.id.tvResetPassword){
            resetPasswordActivity();
        }
    }

    private void resetPasswordActivity() {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
        clearError();
    }

    private void clearError() {
        email.setError(null);
        password.setError(null);
    }

    private void registerActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        clearError();
    }

    private void loginAccount(){
        final String mail = email.getText().toString();
        String pass = password.getText().toString();
        loadingBar.setTitle("Login Account");
        loadingBar.setMessage("Please wait, while we are login to your account for you...");
        loadingBar.show();
        mAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

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
