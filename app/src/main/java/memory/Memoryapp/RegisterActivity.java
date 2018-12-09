package memory.Memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.EditText;

EditText email, firstName, LastName, password;
Button register;
=======
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
>>>>>>> b0f0191a990c2ce6a17319785174b104ae325f39

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener, View.OnClickListener {

    private static boolean valIsDone;

    @NotEmpty()
    @Email()
    private EditText email;
    @NotEmpty()
    @Pattern(message = "Input must contain only letters", regex = "[a-zA-Z][a-zA-Z ]+")
    private EditText firstName;
    @NotEmpty()
    @Pattern(message = "Input must contain only letters", regex = "[a-zA-Z][a-zA-Z ]+")
    private EditText lastName;
    @NotEmpty()
    @Password(message = "Minimum 6 characters")
    private EditText password;

    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.etEmail);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        password = findViewById(R.id.etPassword2);
        findViewById(R.id.btnRegister).setOnClickListener(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
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
        if (valIsDone) {
            switch (v.getId()) {
                case (R.id.btnRegister):
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}
