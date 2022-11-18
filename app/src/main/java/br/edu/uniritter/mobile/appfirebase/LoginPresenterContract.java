package br.edu.uniritter.mobile.appfirebase;

import android.content.Context;

public interface LoginPresenterContract {
    interface view {
        public Context getContexto();
        public void entrar();
        public void message(String msg);
    }
    interface presenter {
        public void logar(String email, String senha);
        public void cadastrarLogin(String email, String senha, String senha2);

    }
}
