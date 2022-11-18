package br.edu.uniritter.mobile.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginPresenterContract.view {
    private LoginPresenterContract.presenter presenter;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new LoginPresenter(this);
        findViewById(R.id.btLogar).setOnClickListener((view)->{
            presenter.logar(((EditText)findViewById(R.id.editEmailLogin)).getText().toString(),
                            ((EditText)findViewById(R.id.editEmailLogin)).getText().toString());
        });
        findViewById(R.id.textView6).setOnClickListener((view)->{
            Intent intent = new Intent(this, NovoLoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public Context getContexto() {
        return this;
    }

    @Override
    public void entrar() {

    }

    @Override
    public void message(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }
}