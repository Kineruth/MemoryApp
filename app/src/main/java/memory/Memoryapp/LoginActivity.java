package memory.Memoryapp;

import android.app.Activity;
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

    private static boolean valIsDone;
    @NotEmpty()
    @Email()
    private EditText email;
    @NotEmpty()
    @Password(min = 1, message = "Minimum 6 characters")
    private EditText password;
    private FirebaseAuth mAuth;
    private FirebaseUser currentuser;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.tvRegister).setOnClickListener(this);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        validator.validate();
        if (valIsDone && v.getId() == R.id.btnLogin) {
            final String mail = email.getText().toString();
            String pass = password.getText().toString();
            mAuth.signInWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
        else if (v.getId() == R.id.tvRegister) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            email.setError(null);
            password.setError(null);
        }
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
