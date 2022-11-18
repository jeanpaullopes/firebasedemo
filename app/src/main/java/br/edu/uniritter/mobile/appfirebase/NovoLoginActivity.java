package br.edu.uniritter.mobile.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class NovoLoginActivity extends AppCompatActivity implements LoginPresenterContract.view{
    LoginPresenterContract.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_login);
        this.presenter = new LoginPresenter(this);
        findViewById(R.id.button).setOnClickListener((view)->{
            presenter.cadastrarLogin(((EditText)findViewById(R.id.editEmailCad)).getText().toString(),
                    ((EditText)findViewById(R.id.editSenha1)).getText().toString(),
                    ((EditText)findViewById(R.id.editSenha2)).getText().toString());
        });
        
    }

    @Override
    public Context getContexto() {
        return this;
    }

    @Override
    public void entrar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }
}