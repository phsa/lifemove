package br.com.lifemove.service;

import br.com.lifemove.listener.SimpleAsynchronousTaskListener;

public class LoginService {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private SimpleAsynchronousTaskListener listener;

    public LoginService(SimpleAsynchronousTaskListener listener) {
        this.listener = listener;
    }

    public void authenticate(String username, String password) {
        if(ADMIN_USERNAME.compareTo(username) == 0 && ADMIN_PASSWORD.compareTo(password) == 0)
            listener.onSuccess();
        else
            listener.onFailure("Usuário ou senha inválido");
    }

}
