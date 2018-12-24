package memory.Memoryapp.Activity.Start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import memory.Memoryapp.Activity.Main.MainActivity;
import memory.Memoryapp.R;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initFields();
        initFireBase();
    }

    private void initFields(){
        findViewById(R.id.agreeNContinueTVBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnagreeNContinueTVBtn();
            }
        });
    }

    private void initFireBase(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    private void clickOnagreeNContinueTVBtn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser != null) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    }
}
