package memory.Memoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    }

    @Override
    public void onClick(View v) {
        validator.validate();
        if (valIsDone && v.getId() == R.id.btnLogin) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
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