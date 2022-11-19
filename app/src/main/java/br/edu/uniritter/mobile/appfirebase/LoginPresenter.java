package br.edu.uniritter.mobile.appfirebase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginPresenterContract.presenter{
    private final String TAG = "LoginPresenter";
    LoginPresenterContract.view view;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public LoginPresenter(LoginPresenterContract.view view) {
        this.view = view;
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        this.user = mAuth.getCurrentUser();

    }

    @Override
    public void logar(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            Log.d(TAG, "onComplete: ");
                            view.message("Authentication successfuly.");
                            view.entrar();
                        } else {
                            view.message("signInWithEmail:failure"+ task.getException());
                        }
                    }
                });

    }

    @Override
    public void cadastrarLogin(String email, String senha, String senha2) {
        if (senha.equals(senha2)) {
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               user = mAuth.getCurrentUser();
                               view.message("cadastro realizado com sucesso");
                               view.entrar();
                               //updateUI(user);
                           } else {
                               view.message("falha no cadastro, " + task.getException().getMessage());

                           }
                       }
                   }

                    );
        } else {
            view.message("senhas não conferem");
        }
    }
}
